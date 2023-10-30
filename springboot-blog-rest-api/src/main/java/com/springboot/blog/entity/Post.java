package com.springboot.blog.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

/*Entity
@Entity:Entities in JPA are POJOs representing data that can be persisted in the database.
An entity represents a table stored in a database.
Every instance of an entity represents a row in the table.

Application Layer sends Entity Objects to Data Layer
Data Layer sends Entity Objects to the Application Layer.
Data fetched from the database will be mapped to an Entity Object.
The object we want to persist in the database has to be mapped to an Entity Object first.
 */
// get\set\toString\hashCode\equals 等方法
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(
        name = "posts",uniqueConstraints = {@UniqueConstraint(columnNames = {"title"}) }
)//set "title" is unique in database and when you use postman or ARC, you have to set title value in different ways,
/*
post in json:

  { "title":"my new post5",                   { "title":"my new post5",
    "description":"post description3",           "description":"post description3",
    "content":"this is my new post3" }            "content":"this is my new post3" }                                                                                  }

*/
public class Post {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )

    private Long id;

    private String title;

    private String description;

    private String content;
    /*CascadeType.ALL: This means that any changes made to the Post entity will be cascaded to the related Comment entities.
    For example, if a Post is deleted, all of its related Comments will also be deleted.*/
    /*orphanRemoval = true: This means that if a Comment is removed from the collection of comments in a Post,
    it will be deleted from the database as well.*/
    @OneToMany(mappedBy = "post",cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<Comment> comments = new HashSet<>();//This declares a set of comments for each post.
    @ManyToOne(fetch = FetchType.LAZY)//lazy means that the category field will not be loaded unless you explicitly call it.
    @JoinColumn(name = "category_id")
    private Category category;
}
