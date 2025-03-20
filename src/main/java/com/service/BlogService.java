package com.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.entity.BlogEntity;
import com.exception.CustomException;
import com.model.BlogDTO;
import com.repository.BlogRepository;

@Service
public class BlogService {
	
	private BlogRepository blogRepository;
	
    // Constructor based Dependency Injection for BlogRepository
	public BlogService(BlogRepository blogRepository) {
		this.blogRepository=blogRepository;
	}
	
	
	
	// Convert BlogDTO object to BlogEntity object
	private BlogEntity convertBlogDTOToEntity(BlogDTO blog) {
	    BlogEntity blogEntity = new BlogEntity();
	    blogEntity.setId(blog.getId());
	    blogEntity.setTitle(blog.getTitle());
	    blogEntity.setContent(blog.getContent());
	    blogEntity.setCreatedAt(blog.getCreatedAt());  //
	    return blogEntity;
	}

	 
	// Convert BlogEntity object to BlogDTO object
	private BlogDTO convertBlogEntityToDTO(BlogEntity blogEntity) {
	    BlogDTO blog = new BlogDTO();
	    blog.setId(blogEntity.getId());
	    blog.setTitle(blogEntity.getTitle());
	    blog.setContent(blogEntity.getContent());
	    blog.setCreatedAt(blogEntity.getCreatedAt()); //
	    return blog;
	}

	

	// Creating new Blog
	public BlogDTO createBlog(BlogDTO blogDTO) {
        BlogEntity savedBlog = blogRepository.save(convertBlogDTOToEntity(blogDTO));
        return convertBlogEntityToDTO(savedBlog);
    }
	
	
	//  Find a Blog by an Id
	public BlogDTO findBlogById(Long id) {
		Optional<BlogEntity> blog=blogRepository.findById(id);
		if(blog.isPresent()) {
			BlogDTO blogDTO=convertBlogEntityToDTO(blog.get());
			return blogDTO;
		}
		else {
			throw new CustomException("No Blog Found With The Given Id");
		}
	}

	
	// Deleting a Blog data by its Id
	public boolean deleteBlogById(Long id) {
		Optional<BlogEntity> blog=blogRepository.findById(id);
		if(blog.isPresent()) {
			blogRepository.deleteById(id);
			return true;
		}
		else {
			throw new CustomException("No Blog Found With The Given Id");
		}
	}
	
	// Updating a Blog by its id
	  public BlogDTO updateBlog(Long id,BlogDTO blogDTO) {
		  Optional<BlogEntity> blog=blogRepository.findById(id);
		  if(blog.isPresent()) {
			  BlogEntity blogEntity=convertBlogDTOToEntity(blogDTO);
			  blogRepository.save(blogEntity);
			  return convertBlogEntityToDTO(blogEntity);
		  }
		  else {
				throw new CustomException("No Blog Found With The Given Id");
			}
	  }
	  
	  
	  // Retrieving all Blogs from database
	  public List<BlogDTO> findAllBlog(){
		  List<BlogEntity> blogs=blogRepository.findAll();
		  return blogs.stream().map(blog->{
			  BlogDTO blogDTO=convertBlogEntityToDTO(blog);
			  return blogDTO;
		  }).collect(Collectors.toList());
	  }
	
}
