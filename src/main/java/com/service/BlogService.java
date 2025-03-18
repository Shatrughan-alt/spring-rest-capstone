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
	  
	  public List<BlogDTO> findAllBlog(){
		  List<BlogEntity> blogs=blogRepository.findAll();
		  return blogs.stream().map(blog->{
			  BlogDTO blogDTO=convertBlogEntityToDTO(blog);
			  return blogDTO;
		  }).collect(Collectors.toList());
	  }
	
}
