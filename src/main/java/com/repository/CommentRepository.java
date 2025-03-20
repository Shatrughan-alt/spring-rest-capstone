package com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.entity.BlogEntity;
import com.entity.CommentEntity;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Long>{
	
	// Retrive All Comments with given Blog
	List<CommentEntity> findByBlog(BlogEntity blog);
}
