package com.greeneyed.samples.webservices.restfulwebservice.service;

import com.greeneyed.samples.webservices.restfulwebservice.entity.Post;
import com.greeneyed.samples.webservices.restfulwebservice.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;

    public List<Post> getAllPostsByUserId(long userId) {
        return postRepository.findAllByUserId(userId);
    }

    public Post savePost(Post post) {
        return postRepository.save(post);
    }

    public Optional<Post> getPostById(long id) {
        return postRepository.findById(id);
    }
}
