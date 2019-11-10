package com.greeneyed.samples.webservices.restfulwebservice.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
public class Post {

    @Id
    @GeneratedValue
    private long id;

    private String message;

    private LocalDateTime createDate;

    private LocalDateTime updateDate;

    @ManyToOne
    private User user;

    public Post(String message, LocalDateTime createDate) {
        this.message = message;
        this.createDate = createDate;
    }

}
