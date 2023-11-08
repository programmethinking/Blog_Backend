package com.springboot.blog.controller.request;

import com.springboot.blog.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryCreateRequest {

    private Long id;
    private String name;
    private String description;

    public Category toEntity() {
        return new Category(id, name, description, new ArrayList<>());
    }
}
