package com.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	
	// Constructor-based dependency injection for BlogService and CommentService
	public BlogController(BlogService blogService, CommentService commentService) {
        this.blogService = blogService;
        this.commentService = commentService;
    }
	
	// Handle HTTP Request to Create a New Blog
	 @PostMapping
	    public ResponseEntity<BlogDTO> createBlog(@Valid @RequestBody BlogDTO blogDTO) {
		 BlogDTO blog=blogService.createBlog(blogDTO);
	        return new ResponseEntity<>(blog,HttpStatus.CREATED);
	    }
	 
	 // Handle HTTP Request to find a Blog by ID
	@GetMapping("/{id}")
	public ResponseEntity<BlogDTO> findBlogById(@PathVariable Long id){
		BlogDTO blogData=blogService.findBlogById(id);
		return new ResponseEntity<>(blogData,HttpStatus.OK);
	}
	
	
	// Handle HTTP Request to Delete a Blog by its ID
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteBlogById(@PathVariable Long id) {
	    blogService.deleteBlogById(id);
	    return ResponseEntity.ok("Blog Deleted Successfully"); 
	}


	
	// Handle HTTP Request to Update a Blog by its ID
	@PutMapping("/{id}")
	public ResponseEntity<BlogDTO> updateBlog(@PathVariable Long id,@Valid @RequestBody BlogDTO blogDTO){
		BlogDTO blog=blogService.updateBlog(id,blogDTO);
		return new ResponseEntity<>(blog,HttpStatus.OK);
	}
	
	
	
	// Handle HTTP Request to Find all Blogs
	@GetMapping
	public ResponseEntity<List<BlogDTO>> findAllBlog(){
		List<BlogDTO> list=blogService.findAllBlog();
		return new ResponseEntity<>(list,HttpStatus.OK);
	}
	
	
	
	
	
	
	
	// Handle HTTP Request to Post Comments 
	 @PostMapping("/comment")
	 public ResponseEntity<CommentDTO> addComment(@Valid @RequestBody CommentDTO commentDTO) {
	     return ResponseEntity.status(201).body(commentService.addComment(commentDTO));
	    }
	 
	// Handle HTTP Request to Find All Comments Available for a BlogID
	 @GetMapping("{id}/comment")
	 public ResponseEntity<List<CommentDTO>> getCommentsByBlogId(@PathVariable Long id) {
	     List<CommentDTO> comments = commentService.getCommentsByBlogId(id);
	     return ResponseEntity.ok(comments);
	 }
	 
	 
	 


}
