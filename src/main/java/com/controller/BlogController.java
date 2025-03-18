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
	public BlogController(BlogService blogService, CommentService commentService) {
        this.blogService = blogService;
        this.commentService = commentService;
    }
	
	 @PostMapping
	    public ResponseEntity<BlogDTO> createBlog(@Valid @RequestBody BlogDTO blogDTO) {
		 BlogDTO blog=blogService.createBlog(blogDTO);
	        return new ResponseEntity<>(blog,HttpStatus.CREATED);
	    }
	@GetMapping("/{id}")
	public ResponseEntity<BlogDTO> findBlogById(@PathVariable Long id){
		BlogDTO blogData=blogService.findBlogById(id);
		return new ResponseEntity<>(blogData,HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteBlogById(@PathVariable Long id) {
	    blogService.deleteBlogById(id);
	    return ResponseEntity.ok("Blog Deleted Successfully"); 
	}


	@PutMapping("/{id}")
	public ResponseEntity<BlogDTO> updateBlog(@PathVariable Long id,@Valid @RequestBody BlogDTO blogDTO){
		BlogDTO blog=blogService.updateBlog(id,blogDTO);
		return new ResponseEntity<>(blog,HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<BlogDTO>> findAllBlog(){
		List<BlogDTO> list=blogService.findAllBlog();
		return new ResponseEntity<>(list,HttpStatus.OK);
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
