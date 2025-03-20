package com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.entity.BlogEntity;
import com.entity.CommentEntity;

/**
 * Repository interface for CommentEntity.
 * Extends JpaRepository to provide CRUD operations.
 */
@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Long>{
	
	/**
     * Retrieves all comments associated with a given blog.
     * */
	List<CommentEntity> findByBlog(BlogEntity blog);
}
