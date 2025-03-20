package com.model;

import java.time.LocalDateTime;

import jakarta.validation.constraints.*;

public class BlogDTO {

	 private Long id;

	    @NotBlank(message = "Title cannot be empty")
	    @Size(min = 3, max = 100, message = "Title must be between 3 and 100 characters")
	    private String title;

	    @NotBlank(message = "Content cannot be empty")
	    @Size(min = 3, max = 200, message = "Content must be between 3 and 200 characters")
	    private String content;

	    private LocalDateTime createdAt;
	    
	    
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
	    
	    
		public BlogDTO(Long id,String title,String content,LocalDateTime createdAt) {
			super();
			this.id=id;
			this.title=title;
			this.content=content;
			this.createdAt = createdAt;
		}
		public BlogDTO() {}
	    
}
