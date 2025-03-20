package com.entity;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


/**
 * Entity class representing a Blog.
 * This class maps to the "blogs" table in the database.
 */
@Entity
@Table(name = "blogs")
public class BlogEntity {
	/**
     * Unique Identifier for each blog entry.
     * The ID is auto-generated using IDENTITY strategy.
     */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	/**
     * Title of the blog post.
     */
	private String title;
	
	/**
     * Content of the blog post.
     */
	private String content;

	/**
     * Timestamp when the blog was created.
     * Defaults to the current UTC time.
     */
	private LocalDateTime createdAt;
	
	@OneToMany(mappedBy = "blog", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CommentEntity> comments;
	
	// Getters and Setters
	
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
	
	public List<CommentEntity> getComments() { return comments; }
    public void setComments(List<CommentEntity> comments) { this.comments = comments; }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
    
    // Constructs a BlogEntity with a title and content
	public BlogEntity(String title, String content) {
		super();
		this.title = title;
		this.content = content;
		this.createdAt = LocalDateTime.now();
	}
	
	// Default Constructor
	public BlogEntity() {
		this.createdAt = LocalDateTime.now();
	}
}
