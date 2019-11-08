package com.greeneyed.samples.webservices.restfulwebservice.service;

import com.greeneyed.samples.webservices.restfulwebservice.dao.UserDao;
import com.greeneyed.samples.webservices.restfulwebservice.entity.Post;
import com.greeneyed.samples.webservices.restfulwebservice.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserDao userDao;

    public List<User> getAllUsers() {
        return userDao.findAll();
    }

    public User getUser(Long id) {
        return userDao.findOne(id);
    }

    public User saveUser(User user) {
        return userDao.save(user);
    }

    public boolean deleteUser(Long id) {
        return userDao.deleteUser(id);
    }

    public List<Post> getAllPostsForUser(long userId) {
        return userDao.findOne(userId).getPosts();
    }

    public Post savePostForUser(long userId, Post post) {
        return userDao.findOne(userId).addPost(post);
    }

    public Post getPostForUserById(long userId, long postId) {
        return userDao.findOne(userId).getPostById(postId);
    }



}
