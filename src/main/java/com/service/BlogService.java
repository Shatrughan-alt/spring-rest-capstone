package com.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.entity.BlogEntity;
import com.exception.CustomException;
import com.model.BlogDTO;
import com.repository.BlogRepository;

/**
 * Service class for handling blog-related operations.
 */
@Service
public class BlogService {
	
	private BlogRepository blogRepository;
	
	/**
     * Constructor-based Dependency Injection for BlogRepository.
     * 
     * */
	public BlogService(BlogRepository blogRepository) {
		this.blogRepository=blogRepository;
	}
	
	
	
	/**
	 * Converts a BlogDTO object to a BlogEntity object.
	 * */
	private BlogEntity convertBlogDTOToEntity(BlogDTO blog) {
	    BlogEntity blogEntity = new BlogEntity();
	    blogEntity.setId(blog.getId());
	    blogEntity.setTitle(blog.getTitle());
	    blogEntity.setContent(blog.getContent());
	    blogEntity.setCreatedAt(blog.getCreatedAt());  //
	    return blogEntity;
	}

	 
	/**
	 * Converts a BlogEntity object to a BlogDTO object.
	 * */
	private BlogDTO convertBlogEntityToDTO(BlogEntity blogEntity) {
	    BlogDTO blog = new BlogDTO();
	    blog.setId(blogEntity.getId());
	    blog.setTitle(blogEntity.getTitle());
	    blog.setContent(blogEntity.getContent());
	    blog.setCreatedAt(blogEntity.getCreatedAt()); //
	    return blog;
	}

	

	/**
	 * Creates a new blog entry.
	 * 
	 * @param blogDTO the DTO containing blog details
	 * @return the created BlogDTO object
	 */
	public BlogDTO createBlog(BlogDTO blogDTO) {
        BlogEntity savedBlog = blogRepository.save(convertBlogDTOToEntity(blogDTO));
        return convertBlogEntityToDTO(savedBlog);
    }
	
	
	/**
	 * Finds a blog by its ID.
	 * 
	 * @param id the ID of the blog
	 * @return the found BlogDTO object
	 * @throws CustomException if the blog is not found
	 */
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

	
	/**
	 * Deletes a blog by its ID.
	 * 
	 * @param id the ID of the blog to be deleted
	 * @return true if deletion was successful
	 * @throws CustomException if the blog is not found
	 */
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
	
	/**
	 * Updates a blog by its ID.
	 * 
	 * @param id the ID of the blog to update
	 * @param blogDTO the updated blog data
	 * @return the updated BlogDTO object
	 * @throws CustomException if the blog is not found
	 */
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
	  
	  
	  /**
		 * Retrieves all blogs from the database.
		 * 
		 * @return a list of BlogDTO objects
		 */
	  public List<BlogDTO> findAllBlog(){
		  List<BlogEntity> blogs=blogRepository.findAll();
		  return blogs.stream().map(blog->{
			  BlogDTO blogDTO=convertBlogEntityToDTO(blog);
			  return blogDTO;
		  }).collect(Collectors.toList());
	  }
	
}
