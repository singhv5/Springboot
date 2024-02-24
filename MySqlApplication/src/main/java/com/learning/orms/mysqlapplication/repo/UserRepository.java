package com.learning.orms.mysqlapplication.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learning.orms.mysqlapplication.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
