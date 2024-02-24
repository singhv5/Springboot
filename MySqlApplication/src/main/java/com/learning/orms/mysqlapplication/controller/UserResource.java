package com.learning.orms.mysqlapplication.controller;

import java.net.URI;
import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;
import java.util.Optional;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.learning.orms.mysqlapplication.entity.Post;
import com.learning.orms.mysqlapplication.entity.User;
import com.learning.orms.mysqlapplication.repo.PostRepository;
import com.learning.orms.mysqlapplication.repo.UserRepository;

@RestController
public class UserResource {
	
	private UserRepository userRepository;
	
	private PostRepository postRepository;
	
	public UserResource(UserRepository userRepository, PostRepository postRepository) {
		super();
		this.userRepository = userRepository;
		this.postRepository = postRepository;
	}


	@GetMapping("/jpa/users")
	public List<User> getAllUsers(){
		return userRepository.findAll();
	}
	
	@GetMapping("/jpa/users/{id}")
	public EntityModel<Optional<User>> retrieveUser(@PathVariable int id) throws UserPrincipalNotFoundException{
		Optional<User> user = userRepository.findById(id);
		if(user.isEmpty())
			throw new UserPrincipalNotFoundException("Id : " + id);
		EntityModel<Optional<User>> entityModel = EntityModel.of(user);
		
		WebMvcLinkBuilder linkBuilder = linkTo(methodOn(this.getClass()).getAllUsers());
		entityModel.add(linkBuilder.withRel("all-users"));
		return entityModel;
	}
	
	@DeleteMapping("/jpa/users/{id}")
	public void deleteUsers(@PathVariable int id){
		 userRepository.deleteById(id);
	}
	
	@PostMapping("/jpa/users")
	public ResponseEntity<User> createUsers(@RequestBody User user){
		User savedUser = userRepository.save(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@GetMapping("/jpa/users/posts")
	public List<Post> getAllPosts(){
		return postRepository.findAll();
	}
	
	@GetMapping("/jpa/users/{id}/posts")
	public List<Post> retrievePost(@PathVariable int id) throws UserPrincipalNotFoundException{
		Optional<User> user = userRepository.findById(id);
		if(user.isEmpty())
			throw new UserPrincipalNotFoundException("Id : " + id);
		return user.get().getPosts();
		/*
		 * EntityModel<Optional<Post>> entity	Model = EntityModel.of(post);
		 * 
		 * WebMvcLinkBuilder linkBuilder =
		 * linkTo(methodOn(this.getClass()).getAllPosts());
		 * entityModel.add(linkBuilder.withRel("all-posts")); return entityModel;
		 */
	}

	@PostMapping("/jpa/users/{id}/posts")
	public ResponseEntity<Object> createPost(@PathVariable int id, @RequestBody Post post) throws UserPrincipalNotFoundException{
		Optional<User> user = userRepository.findById(id);
		if(user.isEmpty())
			throw new UserPrincipalNotFoundException("Id : " + id);
		
		post.setUser(user.get());
		Post savedPost = postRepository.save(post);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedPost.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("/jpa/users/{id}/post")
	public void deletePosts(@PathVariable int id){
		 postRepository.deleteById(id);
	}
}
