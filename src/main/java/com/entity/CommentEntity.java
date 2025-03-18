package com.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "comments")
public class CommentEntity {
    
	// Unique Identifier with Auto-Generated using IDENTITY strategy (Works as a Primary Key)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String comment;

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
