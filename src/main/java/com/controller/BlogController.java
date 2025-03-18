package com.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.model.BlogDTO;
import com.model.CommentDTO;
import com.service.BlogService;
import com.service.CommentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/blogs")
@Validated
public class BlogController {
	private BlogService blogService;
	private CommentService commentService;
	public BlogController(BlogService blogService, CommentService commentService) {
        this.blogService = blogService;
        this.commentService = commentService;
    }
	
	 @PostMapping
	    public ResponseEntity<BlogDTO> createBlog(@Valid @RequestBody BlogDTO blogDTO) {
	        return ResponseEntity.status(201).body(blogService.createBlog(blogDTO));
	    }
	
	 @PostMapping("/comment")
	    public ResponseEntity<CommentDTO> addComment(@Valid @RequestBody CommentDTO commentDTO) {
	        return ResponseEntity.status(201).body(commentService.addComment(commentDTO));
	    }
	 @GetMapping("{id}/comment")
	 public ResponseEntity<List<CommentDTO>> getCommentsByBlogId(@PathVariable Long id) {
	     List<CommentDTO> comments = commentService.getCommentsByBlogId(id);
	     return ResponseEntity.ok(comments);
	 }


}
