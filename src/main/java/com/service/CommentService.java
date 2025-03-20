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



/**
 * Service class for handling comment-related operations.
 */
@Service
public class CommentService {

	private CommentRepository commentRepository;
	private BlogRepository blogRepository;
	
	/**
	 * Constructor-based dependency injection for CommentRepository and BlogRepository.
	 * 
	 * @param commentRepository the repository for comment operations
	 * @param blogRepository the repository for blog operations
	 */
	public CommentService(CommentRepository commentRepository, BlogRepository blogRepository) {
        this.commentRepository = commentRepository;
        this.blogRepository = blogRepository;
    }
	
	
	/**
	 * Converts a CommentDTO object to a CommentEntity object.
	 * 
	 * @param comment the DTO object containing comment data
	 * @param blogEntity the associated blog entity
	 * @return the corresponding CommentEntity object
	 */
	private CommentEntity convertCommentDTOToEntity(CommentDTO comment, BlogEntity blogEntity) {
	    CommentEntity commentEntity = new CommentEntity();
	    commentEntity.setId(comment.getId());
	    commentEntity.setComment(comment.getComment());
	    commentEntity.setBlog(blogEntity);
	    return commentEntity;
	}

	
	/**
	 * Converts a CommentEntity object to a CommentDTO object.
	 * 
	 * @param commentEntity the entity object containing comment data
	 * @return the corresponding CommentDTO object
	 */
	private CommentDTO convertCommentEntityToDTO(CommentEntity commentEntity) {
	    CommentDTO commentDTO = new CommentDTO();
	    commentDTO.setId(commentEntity.getId());
	    commentDTO.setComment(commentEntity.getComment());
	    commentDTO.setBlogId(commentEntity.getBlog().getId());
	    return commentDTO;
	}
	
	

	/**
	 * Adds a new comment to a blog.
	 * 
	 * @param commentDTO the DTO containing comment details
	 * @return the created CommentDTO object
	 * @throws CustomException if the blog is not found
	 */
	 public CommentDTO addComment(CommentDTO commentDTO) {
	        BlogEntity blogEntity = blogRepository.findById(commentDTO.getBlogId())
	                .orElseThrow(() -> new CustomException("Blog not found"));

	        CommentEntity savedComment = commentRepository.save(convertCommentDTOToEntity(commentDTO, blogEntity));
	        return convertCommentEntityToDTO(savedComment);
	    }
	 
	 
	 /**
		 * Retrieves all comments for a given blog ID.
		 * 
		 * @param blogId the ID of the blog
		 * @return a list of CommentDTO objects
		 * @throws CustomException if the blog is not found
		 */
	 public List<CommentDTO> getCommentsByBlogId(Long blogId) {
	     BlogEntity blogEntity = blogRepository.findById(blogId)
	             .orElseThrow(() -> new CustomException("Blog not found"));

	     List<CommentEntity> commentEntities = commentRepository.findByBlog(blogEntity);
	     return commentEntities.stream()
                 .map(comment -> convertCommentEntityToDTO(comment))
                 .collect(Collectors.toList());

	 }
	 
	 /**
		 * Deletes a comment by its ID.
		 * 
		 * @param id the ID of the comment to be deleted
		 * @throws CustomException if the comment is not found
		 */
	 public void deleteCommentById(Long id) {
			Optional<CommentEntity> comment=commentRepository.findById(id);
			if(comment.isPresent()) {
				commentRepository.deleteById(id);
			}
			else {
				throw new CustomException("No Comment Found With The Given Id");
			}
		} 
	 
	 /**
		 * Updates a comment by its ID.
		 * 
		 * @param id the ID of the comment to update
		 * @param commentDTO the updated comment data
		 * @return the updated CommentDTO object
		 * @throws CustomException if the comment or blog is not found
		 */
	 public CommentDTO updateComment(Long id,CommentDTO commentDTO) {
		 CommentEntity existingComment = commentRepository.findById(id)
		            .orElseThrow(() -> new CustomException("Comment not found"));
		 BlogEntity blogEntity = blogRepository.findById(commentDTO.getBlogId())
		            .orElseThrow(() -> new CustomException("Blog not found"));
		 
		 existingComment.setComment(commentDTO.getComment());
		    existingComment.setBlog(blogEntity);
		    CommentEntity updatedComment = commentRepository.save(existingComment);
		    return convertCommentEntityToDTO(updatedComment);
	 }

	 /**
		 * Retrieves all comments from the database.
		 * 
		 * @return a list of CommentDTO objects
		 */
	public List<CommentDTO> findAllComments(){
		List<CommentEntity> list=commentRepository.findAll();
		return list.stream().map(comment->{
			CommentDTO commentDTO=convertCommentEntityToDTO(comment);
			return commentDTO;
		}).collect(Collectors.toList());
	}
}
