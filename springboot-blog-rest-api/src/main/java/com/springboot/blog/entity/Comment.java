package com.springboot.blog.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data// get\set\toString\hashCode\equals 等方法
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )

    private long id;

    private String name;

    private String email;

    private String body;
    // FetchType.LAZY tells Hibernate used to decide whether to load all the fields of the entity immediately or to load them on demand
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="post_id",nullable = false)//foreign key
    private Post post;//many comments belongs to one post

}
