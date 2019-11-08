package com.greeneyed.samples.webservices.restfulwebservice.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Component
@Data
@NoArgsConstructor
@Entity
public class Post {

    @Id
    @GeneratedValue
    private long id;

    private String title;

    private String text;

    private LocalDateTime createDate;

    private LocalDateTime updateDate;

    @ManyToOne
    private User user;

    public Post(String title, String text, LocalDateTime createDate) {
        this.title = title;
        this.text = text;
        this.createDate = createDate;
    }

}
