package com.learning.orms.mysqlapplication.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learning.orms.mysqlapplication.entity.Post;

public interface PostRepository extends JpaRepository<Post, Integer> {

}
