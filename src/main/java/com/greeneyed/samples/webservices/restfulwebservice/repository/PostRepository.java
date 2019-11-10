package com.greeneyed.samples.webservices.restfulwebservice.repository;

import com.greeneyed.samples.webservices.restfulwebservice.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findAllByUserId(Long userId);

    Optional<Post> findById(Long id);
}
