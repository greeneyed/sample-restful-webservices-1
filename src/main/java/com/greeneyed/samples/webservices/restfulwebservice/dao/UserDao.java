package com.greeneyed.samples.webservices.restfulwebservice.dao;

import com.greeneyed.samples.webservices.restfulwebservice.exception.NotFoundException;
import com.greeneyed.samples.webservices.restfulwebservice.entity.Post;
import com.greeneyed.samples.webservices.restfulwebservice.entity.User;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class UserDao {

    private static List<User> users = new ArrayList<>();

    static {
        User amanda = new User(1, "Amanda", new Date());
        amanda.addPost(new Post(11, "olala", "i'm fabulous!"));
        amanda.addPost(new Post(22, "hello world", "why so serious?"));
        users.add(amanda);
        users.add(new User(2, "Helen", new Date()));
        users.add(new User(3, "David", new Date()));
        users.add(new User(4, "James", new Date()));


    }

    public UserDao() {}

    public List<User> findAll() {
        return users;
    }

    public User save(User user) {
        if (user != null) {
            if (user.getId() == null || user.getId() == 0) {
                user.setId(users.size()+1);
            }
            users.add(user);
        }
        return user;
    }

    public User findOne(int id) throws NoSuchElementException {
        return users
                .stream()
                .filter(user -> user.getId() == id)
                .findFirst()
                .orElseThrow(() -> new NotFoundException("user id=" + id));
    }

    public boolean deleteUser(int userId) {
        return users.remove(findOne(userId));
    }

    public List<Post> getAllPostsForUser(int userId) {
        return findOne(userId).getPosts();
    }

    public Post savePostForUser(int userId, Post post) {
        return findOne(userId).addPost(post);
    }

    public Post getPostForUserById(int userId, int postId) {
        return findOne(userId).getPostById(postId);
    }
}
