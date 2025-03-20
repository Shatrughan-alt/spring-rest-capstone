package com.model;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

import jakarta.validation.constraints.*;


/**
 * Data Transfer Object (DTO) for the Blog entity.
 * This class is used for transferring blog data between layers
 * without exposing the actual entity.
 */
public class BlogDTO {

	/**
     * Unique identifier of the blog.
     */
	 private Long id;

	 /**
	     * Title of the blog post.
	     * - Cannot be empty.
	     * - Must be between 3 and 100 characters.
	     */
	    @NotBlank(message = "Title cannot be empty")
	    @Size(min = 3, max = 100, message = "Title must be between 3 and 100 characters")
	    private String title;

	    /**
	     * Content of the blog post.
	     * - Cannot be empty.
	     * - Must be between 3 and 200 characters.
	     */
	    @NotBlank(message = "Content cannot be empty")
	    @Size(min = 3, max = 200, message = "Content must be between 3 and 200 characters")
	    private String content;

	    /**
	     * Timestamp indicating when the blog was created.
	     */
	    private LocalDateTime createdAt;
	    
	    //Getters and Setters
		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getContent() {
			return content;
		}

		public void setContent(String content) {
			this.content = content;
		}

		public LocalDateTime getCreatedAt() {
	        return createdAt;
	    }

	    public void setCreatedAt(LocalDateTime createdAt) {
	        this.createdAt = createdAt;
	    }
	    
	    
	    /**
	     * Constructor to initialize BlogDTO with all fields.
	     * */
		public BlogDTO(Long id,String title,String content,LocalDateTime createdAt) {
			super();
			this.id=id;
			this.title=title;
			this.content=content;
			this.createdAt = createdAt;
		}
		
		/**
	     * Default constructor.
	     */

		public BlogDTO() {}
	    
}
