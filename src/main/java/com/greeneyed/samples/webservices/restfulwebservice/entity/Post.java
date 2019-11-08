package com.greeneyed.samples.webservices.restfulwebservice.entity;

import org.springframework.stereotype.Component;

import javax.persistence.*;

@Component
@Entity
public class Post {

    @Id
    @GeneratedValue
    private int id;

    private String title;

    private String text;

    @ManyToOne
    private User user;

    public Post() {
    }

    public Post(int id, String title, String text) {
        this.id = id;
        this.title = title;
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
