package com.greeneyed.samples.webservices.restfulwebservice.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@ApiModel(description = "User details")
@Data
@NoArgsConstructor
@Component
//@JsonFilter("UserIdFilter")   //for now works only with FilteringController but not UserController
@Entity
public class User {

    @Id
    @GeneratedValue
    private Integer id;

    @Size(min = 2)
    @ApiModelProperty(notes = "Name should be at least 2 characters")
    private String username;

    @Past
    @ApiModelProperty(notes = "Birth date should be in the past")
    private LocalDate birthDate;

    @OneToMany(mappedBy = "user")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Post> posts = new ArrayList<>();

    public User(@Size(min = 2) String username, @Past LocalDate birthDate) {
        this.username = username;
        this.birthDate = birthDate;
    }

    public Post addPost(Post post) {
        posts.add(post);
        return post;
    }

    public Post getPostById(int id) {
        return posts
                .stream()
                .filter(post -> post.getId() == id)
                .findFirst()
                .orElseThrow(NoSuchElementException::new);
    }

}
