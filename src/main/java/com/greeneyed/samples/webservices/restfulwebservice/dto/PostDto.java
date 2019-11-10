package com.greeneyed.samples.webservices.restfulwebservice.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class PostDto {

    private int id;

    private String message;

    private LocalDateTime createDate;

    private LocalDateTime updateDate;

}
