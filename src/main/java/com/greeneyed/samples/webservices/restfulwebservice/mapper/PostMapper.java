package com.greeneyed.samples.webservices.restfulwebservice.mapper;

import com.greeneyed.samples.webservices.restfulwebservice.dto.PostDto;
import com.greeneyed.samples.webservices.restfulwebservice.entity.Post;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface PostMapper {

    PostDto toDto(Post entity);

    Post toEntity(PostDto dto);

    List<PostDto> toDtoList(List<Post> entityList);
}
