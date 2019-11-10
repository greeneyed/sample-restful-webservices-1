package com.greeneyed.samples.webservices.restfulwebservice.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@ApiModel(description = "User details")
@Data
@NoArgsConstructor
public class UserDto {

    private Long id;

    @ApiModelProperty(notes = "Name should be at least 2 characters")
    @Size(min = 2)
    private String username;

    private String firstName;

    private String lastName;

    @ApiModelProperty(notes = "Birth date should be in the past")
    @Past
    private LocalDate birthDate;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<PostDto> posts = new ArrayList<>();
}
