package com.entity;

import jakarta.persistence.*;


/**
 * Entity class representing a Comment.
 * This class maps to the "comments" table in the database.
 */
@Entity
@Table(name = "comments")
public class CommentEntity {
    
	/**
     * Unique Identifier for each comment entry.
     * The ID is auto-generated using IDENTITY strategy.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Content of the comment.
     * Cannot be null and has a maximum length of 200 characters.
     */
    @Column(nullable = false, length = 200)
    private String comment;

    /**
     * Many-to-One relationship with BlogEntity.
     * Each comment belongs to a single blog post.
     */
    @ManyToOne
    @JoinColumn(name = "blog_id", nullable = false)
    private BlogEntity blog;

    // Default Constructor
    public CommentEntity() {}

    // Constructs a CommentEntity with comment and blog
    public CommentEntity(String comment, BlogEntity blog) {
        this.comment = comment;
        this.blog = blog;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }

    public BlogEntity getBlog() { return blog; }
    public void setBlog(BlogEntity blog) { this.blog = blog; }
}
