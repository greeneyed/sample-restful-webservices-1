package com.greeneyed.samples.webservices.restfulwebservice.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Data
@NoArgsConstructor
@Component
//@JsonFilter("UserIdFilter")   //for now works only with FilteringController but not UserController
@Entity
public class User {

    @Id
    @GeneratedValue
    private Long id;

    private String username;

    private String firstName;

    private String lastName;

    private LocalDate birthDate;

    @OneToMany(mappedBy = "user")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Post> posts = new ArrayList<>();

    public User(String username, LocalDate birthDate) {
        this.username = username;
        this.birthDate = birthDate;
    }

    public Post addPost(Post post) {
        posts.add(post);
        return post;
    }

    public Post getPostById(long id) {
        return posts
                .stream()
                .filter(post -> post.getId() == id)
                .findFirst()
                .orElseThrow(NoSuchElementException::new);
    }

}
