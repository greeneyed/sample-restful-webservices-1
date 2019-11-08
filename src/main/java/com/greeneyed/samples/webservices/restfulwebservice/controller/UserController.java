package com.greeneyed.samples.webservices.restfulwebservice.controller;

import com.greeneyed.samples.webservices.restfulwebservice.dto.PostDto;
import com.greeneyed.samples.webservices.restfulwebservice.dto.UserDto;
import com.greeneyed.samples.webservices.restfulwebservice.mapper.PostMapper;
import com.greeneyed.samples.webservices.restfulwebservice.mapper.UserMapper;
import com.greeneyed.samples.webservices.restfulwebservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

//import com.greeneyed.samples.webservices.restfulwebservice.entity.Post;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    private final UserMapper userMapper;

    private final PostMapper postMapper;

    @GetMapping(path = "/users")
    public List<UserDto> getAllUsers() {
        return userMapper.toDtoList(userService.getAllUsers());
    }

    @GetMapping(path = "/users/{id}", produces = "application/hal+json")
    public Resource<UserDto> getUserById(@PathVariable long id) {
        UserDto user = userMapper.toDto(userService.getUser(id));
        Resource<UserDto> resource = new Resource<>(user);
        ControllerLinkBuilder allUsersLink = linkTo(methodOn(this.getClass()).getAllUsers());
        resource.add(allUsersLink.withRel("all-users"));
        return resource;
    }

    @PostMapping(path = "/users")
    public ResponseEntity<Object> saveUser(@Valid @RequestBody UserDto user) {
        if (user == null) {
            return ResponseEntity.noContent().build();
        }
        UserDto savedUser = userMapper.toDto(userService.saveUser(userMapper.toEntity(user)));
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/users/{userId}")
    public ResponseEntity<Object> deleteUserById(@PathVariable long userId) {
        if (!userService.deleteUser(userId)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "/users/{userId}/posts")
    public List<PostDto> getAllPostsForUser(@PathVariable long userId) {
        return postMapper.toDtoList(userService.getAllPostsForUser(userId));
    }

    @PostMapping(path = "/users/{userId}/posts")
    public ResponseEntity<Object> savePost(@PathVariable long userId, @RequestBody PostDto post) {
        System.out.println("USER ID: " + userId + " POST: " + post);
        if (post == null) {
            return ResponseEntity.noContent().build();
        }
        PostDto savedPost = postMapper.toDto(userService.savePostForUser(userId, postMapper.toEntity(post)));
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedPost)
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping(path = "/users/{userId}/posts/{postId}")
    public PostDto getPostForUserById(@PathVariable long userId, @PathVariable long postId) {
        return postMapper.toDto(userService.getPostForUserById(userId, postId));
    }
}
