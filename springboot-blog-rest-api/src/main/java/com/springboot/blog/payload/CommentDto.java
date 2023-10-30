package com.springboot.blog.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CommentDto {
    private long id;
    @NotEmpty(message ="name should be not null and empty")
    private String name;
    @NotEmpty(message ="email should be not null and empty")
    @Email//email field validation
    private String email;
    @NotEmpty
    @Size(min = 10, message = "body should min 10 characters")
    private String body;

}
