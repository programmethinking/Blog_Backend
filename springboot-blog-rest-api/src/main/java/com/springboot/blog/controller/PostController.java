package com.springboot.blog.controller;

import com.springboot.blog.payload.PostDto;
import com.springboot.blog.payload.PostResponse;
import com.springboot.blog.service.PostService;
import com.springboot.blog.utils.AppConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//controller: API layer
// controller class depends on service class
//inject PostDto into this layer
@RestController//contains @ResponseBody which converts java into json and @Controller
@Tag(
        name = "CRUD REST APIs for Post Resource"
)
@RequestMapping("/api/posts")
public class PostController {
    private PostService postService;
    //no @autowire because this is only constructor
    public PostController(PostService postService) {
        this.postService = postService;
    }
    @Operation(
            summary = "Create Post REST API",
            description = "Create Post REST API is used to save post into database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Http Status 201 CREATED"
    )
    @SecurityRequirement(
            name = "Bear Authentication"
    )
    //create blog post rest api
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping

    public ResponseEntity<PostDto>createPost(@Valid @RequestBody PostDto postDto){
        return new ResponseEntity<>(postService.createPost(postDto),HttpStatus.CREATED);
    }
    @Operation(
            summary = "Get All Posts REST API",
            description = "Get All Posts REST API is used to fetch all the posts from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    //get all posts rest api
    @GetMapping
    public PostResponse getAllPosts(
            //required = false,means the require parameter is optional.
            // if this is set to =true,and you miss this specified request parameter,
            // the server will respond with errors

            //creat constants and use it in respective places,do not duplicate hard code values
            @RequestParam(value ="pageNo",defaultValue = AppConstants.DEFULT_PAGE_NUMBER,required = false)int pageNo,
            @RequestParam(value ="pageSize",defaultValue = AppConstants.DEFULT_PAGE_SIZE,required = false)int pageSize,
            @RequestParam(value ="sortBy",defaultValue = AppConstants.DEFULT_SORT_BY,required = false) String sortBy,
            @RequestParam(value ="sorDir",defaultValue = AppConstants.DEFULT_SORT_DIRECTION,required = false) String sortDir
    ){

        return postService.getAllPosts(pageNo,pageSize,sortBy,sortDir);
    }
    @Operation(
            summary = "Get Post By Id REST API",
            description = "Get Post By Id REST API is used to get single post from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    // get post by id
    @GetMapping("/{id}")//@PathVariable: map /{id} with long id
    public ResponseEntity<PostDto> getPostById(@PathVariable(name = "id")long id){
        return ResponseEntity.ok(postService.getPostById(id));
    }
    @Operation(
            summary = "update Post REST API",
            description = "Update Post REST API is used to update a particular post in the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @SecurityRequirement(name = "Bear Authentication")
    //update post by id rest api
    @PreAuthorize("hasRole('ADMIN')")//在SecurityConfig设置的.requestMatchers("/api/auth/**").permitAll(),但是仍然需要这个ADMIN才能删除
    @PutMapping("/{id}")//annotate handler method to handle HTTP PUT request
    public ResponseEntity<PostDto> updatePost(@Valid  @RequestBody PostDto postDto, @PathVariable(name = "id")long id){
        PostDto postResponse = postService.updatePost(postDto,id );
        return new ResponseEntity<>(postResponse, HttpStatus.OK);
    }
    @Operation(
            summary = "Delete Post REST API",
            description = "Delete Post REST API is used to delete a particular post from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @SecurityRequirement(name = "Bear Authentication")
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deletePost( @PathVariable(name = "id")long id){
        postService.deletePostById(id);
        return new ResponseEntity<>("Post entity deleted successfully.",HttpStatus.OK);
    }
    // Build Get Posts by Category REST API
    // http://localhost:8080/api/posts/category/3
    @GetMapping("/category/{id}")
    public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable("id") Long categoryId){
        List<PostDto> postDtos = postService.getPostsByCategory(categoryId);
        return ResponseEntity.ok(postDtos);
    }
}
