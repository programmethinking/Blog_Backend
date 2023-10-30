package com.springboot.blog.repository;

import com.springboot.blog.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
/* DAO:persistence layer
get CRUD methods for Post entity to talk with Database*/

/*no need to write annotation @Repository to indicate that the class provides the mechanism for
storage, retrieval, update, delete and search operation on objects.
explanation:
SimpleJpaRepository implements JpaRepositoryImplementation
JpaRepositoryImplementation(interface) extends JpaRepository(interface)
SimpleJpaRepository class implements JpaRepositoryImplementation(interface) and JpaRepository(interface)
you can call all the class's method which implement JpaRepository interface
so, That PostRepository extends JpaRepository means PostRepository can call SimpleJpaRepository methods which has crud method
SimpleJpaRepository annotated with @Repository,so it already has crud methods
refer to my notes
*/

public interface PostRepository extends JpaRepository<Post ,Long> {
    //   Long of <Post ,Long> because of private Long id in Post Class
    List<Post> findByCategoryId(Long categoryId);
}
