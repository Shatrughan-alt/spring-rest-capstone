package com.service;

import org.springframework.stereotype.Service;

import com.entity.BlogEntity;
import com.model.BlogDTO;
import com.repository.BlogRepository;

@Service
public class BlogService {
	
	private BlogRepository blogRepository;
	public BlogService(BlogRepository blogRepository) {
		this.blogRepository=blogRepository;
	}
	
	
	
	
	private BlogEntity convertBlogDTOToEntity(BlogDTO blog) {
	    BlogEntity blogEntity = new BlogEntity();
	    blogEntity.setId(blog.getId());
	    blogEntity.setTitle(blog.getTitle());
	    blogEntity.setContent(blog.getContent());
	    return blogEntity;
	}

	private BlogDTO convertBlogEntityToDTO(BlogEntity blogEntity) {
	    BlogDTO blog = new BlogDTO();
	    blog.setId(blogEntity.getId());
	    blog.setTitle(blogEntity.getTitle());
	    blog.setContent(blogEntity.getContent());
	    return blog;
	}

	

	public BlogDTO createBlog(BlogDTO blogDTO) {
        BlogEntity savedBlog = blogRepository.save(convertBlogDTOToEntity(blogDTO));
        return convertBlogEntityToDTO(savedBlog);
    }


	  
	
}
