package com.marynaukrainska.udemy.rest.webservices.restfulwebservices.controllers;

import com.marynaukrainska.udemy.rest.webservices.restfulwebservices.beans.Post;
import com.marynaukrainska.udemy.rest.webservices.restfulwebservices.beans.User;
import com.marynaukrainska.udemy.rest.webservices.restfulwebservices.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

@RestController
public class UserController {

    @Autowired
    private UserDao userDao;

    @GetMapping(path = "/users")
    public List<User> getAllUsers() {
        return userDao.findAll();
    }

    @GetMapping(path = "/users/{id}", produces = "application/hal+json")
    public Resource<User> getUserById(@PathVariable int id) {
        User user = userDao.findOne(id);
        Resource<User> resource = new Resource<>(user);
        ControllerLinkBuilder allUsersLink = linkTo(methodOn(this.getClass()).getAllUsers());
        resource.add(allUsersLink.withRel("all-users"));
        return resource;
    }

    @PostMapping(path = "/users")
    public ResponseEntity<Object> saveUser(@Valid @RequestBody User user) {
        if (user == null) {
            return ResponseEntity.noContent().build();
        }
        User savedUser = userDao.save(user);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/users/{userId}")
    public ResponseEntity<Object> deleteUserById(@PathVariable int userId) {
        if (!userDao.deleteUser(userId)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "/users/{userId}/posts")
    public List<Post> getAllPostsForUser(@PathVariable int userId) {
        return userDao.getAllPostsForUser(userId);
    }

    @PostMapping(path = "/users/{userId}/posts")
    public ResponseEntity<Object> savePost(@PathVariable int userId, @RequestBody Post post) {
        System.out.println("USER ID: " + userId + " POST: " + post);
        if (post == null) {
            return ResponseEntity.noContent().build();
        }
        Post savedPost = userDao.savePostForUser(userId, post);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedPost)
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping(path = "/users/{userId}/posts/{postId}")
    public Post getPostForUserById(@PathVariable int userId, @PathVariable int postId) {
        return userDao.getPostForUserById(userId, postId);
    }
}
