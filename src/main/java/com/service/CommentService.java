package com.service;

import java.util.List;
import java.util.Optional;
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
	
	// Constructor-based dependency injection for CommentRepository and BlogRepository
	public CommentService(CommentRepository commentRepository, BlogRepository blogRepository) {
        this.commentRepository = commentRepository;
        this.blogRepository = blogRepository;
    }
	
	
	// Convert CommentDTO object to CommentEntity object
	private CommentEntity convertCommentDTOToEntity(CommentDTO comment, BlogEntity blogEntity) {
	    CommentEntity commentEntity = new CommentEntity();
	    commentEntity.setId(comment.getId());
	    commentEntity.setComment(comment.getComment());
	    commentEntity.setBlog(blogEntity);
	    return commentEntity;
	}

	
	// Convert CommentEntity object to CommentDTO object
	private CommentDTO convertCommentEntityToDTO(CommentEntity commentEntity) {
	    CommentDTO commentDTO = new CommentDTO();
	    commentDTO.setId(commentEntity.getId());
	    commentDTO.setComment(commentEntity.getComment());
	    commentDTO.setBlogId(commentEntity.getBlog().getId());
	    return commentDTO;
	}
	
	

	// Posting or Adding Comment 
	 public CommentDTO addComment(CommentDTO commentDTO) {
	        BlogEntity blogEntity = blogRepository.findById(commentDTO.getBlogId())
	                .orElseThrow(() -> new CustomException("Blog not found"));

	        CommentEntity savedComment = commentRepository.save(convertCommentDTOToEntity(commentDTO, blogEntity));
	        return convertCommentEntityToDTO(savedComment);
	    }
	 
	 
	 // Find All Comments using BlogID
	 public List<CommentDTO> getCommentsByBlogId(Long blogId) {
	     BlogEntity blogEntity = blogRepository.findById(blogId)
	             .orElseThrow(() -> new CustomException("Blog not found"));

	     List<CommentEntity> commentEntities = commentRepository.findByBlog(blogEntity);
	     return commentEntities.stream()
                 .map(comment -> convertCommentEntityToDTO(comment))
                 .collect(Collectors.toList());

	 }
	 
	 // Deleting Comment by its Id
	 public boolean deleteCommentById(Long id) {
			Optional<CommentEntity> comment=commentRepository.findById(id);
			if(comment.isPresent()) {
				commentRepository.deleteById(id);
				return true;
			}
			else {
				throw new CustomException("No Comment Found With The Given Id");
			}
		} 

}
