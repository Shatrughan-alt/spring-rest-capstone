package com.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.entity.BlogEntity;
import com.entity.CommentEntity;
import com.exception.CustomException;
import com.model.CommentDTO;
import com.repository.BlogRepository;
import com.repository.CommentRepository;

@Service
public class CommentService {

	private CommentRepository commentRepository;
	private BlogRepository blogRepository;
	public CommentService(CommentRepository commentRepository, BlogRepository blogRepository) {
        this.commentRepository = commentRepository;
        this.blogRepository = blogRepository;
    }
	
	private CommentEntity convertCommentDTOToEntity(CommentDTO comment, BlogEntity blogEntity) {
	    CommentEntity commentEntity = new CommentEntity();
	    commentEntity.setId(comment.getId());
	    commentEntity.setComment(comment.getComment());
	    commentEntity.setBlog(blogEntity);
	    return commentEntity;
	}

	private CommentDTO convertCommentEntityToDTO(CommentEntity commentEntity) {
	    CommentDTO commentDTO = new CommentDTO();
	    commentDTO.setId(commentEntity.getId());
	    commentDTO.setComment(commentEntity.getComment());
	    commentDTO.setBlogId(commentEntity.getBlog().getId());
	    return commentDTO;
	}
	
	

	 public CommentDTO addComment(CommentDTO commentDTO) {
	        BlogEntity blogEntity = blogRepository.findById(commentDTO.getBlogId())
	                .orElseThrow(() -> new CustomException("Blog not found"));

	        CommentEntity savedComment = commentRepository.save(convertCommentDTOToEntity(commentDTO, blogEntity));
	        return convertCommentEntityToDTO(savedComment);
	    }
	 
	 public List<CommentDTO> getCommentsByBlogId(Long blogId) {
	     BlogEntity blogEntity = blogRepository.findById(blogId)
	             .orElseThrow(() -> new CustomException("Blog not found"));

	     List<CommentEntity> commentEntities = commentRepository.findByBlog(blogEntity);
	     return commentEntities.stream()
                 .map(comment -> convertCommentEntityToDTO(comment))
                 .collect(Collectors.toList());

	 }

}
