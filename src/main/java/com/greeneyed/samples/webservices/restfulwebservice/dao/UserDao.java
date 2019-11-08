package com.greeneyed.samples.webservices.restfulwebservice.dao;

import com.greeneyed.samples.webservices.restfulwebservice.entity.Post;
import com.greeneyed.samples.webservices.restfulwebservice.entity.User;
import com.greeneyed.samples.webservices.restfulwebservice.exception.NotFoundException;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Component
public class UserDao {

    private static List<User> users = new ArrayList<>();

    static {
        User amanda = new User("Amanda", LocalDate.of(1987, 7, 18));
        amanda.addPost(new Post("olala", "i'm fabulous!", LocalDateTime.now()));
        amanda.addPost(new Post("hello world", "why so serious?", LocalDateTime.of(2007, 9, 30, 0 , 0)));
        users.add(amanda);
        users.add(new User("Helen", LocalDate.of(1965, 2,22)));
        users.add(new User("David", LocalDate.of(2006, 4, 14)));
        users.add(new User("James", LocalDate.of(1996, 10, 18)));
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
