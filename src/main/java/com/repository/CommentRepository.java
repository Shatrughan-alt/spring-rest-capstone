package com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entity.BlogEntity;
import com.entity.CommentEntity;

public interface CommentRepository extends JpaRepository<CommentEntity, Long>{
	
	// Retrive All Comments with given Blog
	List<CommentEntity> findByBlog(BlogEntity blog);
}
