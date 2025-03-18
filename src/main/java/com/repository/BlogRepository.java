package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entity.BlogEntity;

public interface BlogRepository extends JpaRepository<BlogEntity, Long>{

}
