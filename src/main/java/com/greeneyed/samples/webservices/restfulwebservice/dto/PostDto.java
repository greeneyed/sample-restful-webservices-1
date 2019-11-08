package com.greeneyed.samples.webservices.restfulwebservice.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class PostDto {

    private int id;

    private String title;

    private String text;

    private LocalDateTime createDate;

    private LocalDateTime updateDate;

}
