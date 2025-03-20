package com.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotNull;


/**
 * Data Transfer Object (DTO) for the Comment entity.
 * This class is used to transfer comment data between layers
 * without exposing the actual entity.
 */
public class CommentDTO {

	/**
     * Unique identifier of the comment.
     */
    private Long id;

    /**
     * ID of the blog post to which the comment belongs.
     * - Cannot be null.
     */
    @NotNull(message = "Blog ID cannot be null")
    private Long blogId;

    /**
     * Content of the comment.
     * - Cannot be empty.
     * - Must be between 3 and 200 characters.
     */
    @NotBlank(message = "Comment cannot be empty")
    @Size(min = 3, max = 200, message = "Comment must be between 3 and 200 characters")
    private String comment;
    
    //Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getBlogId() { return blogId; }
    public void setBlogId(Long blogId) { this.blogId = blogId; }

    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }
    
    
    /**
     * Constructor to initialize a CommentDTO with all fields.
     * */
    public CommentDTO(Long id, Long blogId, String comment) {
    	super();
        this.id = id;
        this.blogId = blogId;
        this.comment = comment;
    }
    
    /**
     * Default constructor.
     */
    public CommentDTO() {}

 
}
