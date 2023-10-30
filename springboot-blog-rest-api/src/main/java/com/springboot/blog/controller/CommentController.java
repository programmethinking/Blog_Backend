package com.springboot.blog.controller;

import com.springboot.blog.entity.Comment;
import com.springboot.blog.payload.CommentDto;
import com.springboot.blog.payload.PostDto;
import com.springboot.blog.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class CommentController {
    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }
    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDto> create(@PathVariable(value = "postId") long postId,
                                             @Valid @RequestBody CommentDto commentDto){// for json form
        return new ResponseEntity<>(commentService.createComment(postId,commentDto), HttpStatus.CREATED);
    }
    @GetMapping("/posts/{postId}/comments")//目的是得到同一post id下面的所有comment
    public List<CommentDto> getCommentsByPostId(@PathVariable(value = "postId")Long postId){
        return commentService.getCommentsByPostId(postId);
    }
    @GetMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<CommentDto> getCommentByPostId(@PathVariable(value = "postId")Long postId,
                                                         @PathVariable(value = "id")Long commentId){
        CommentDto commentDto = commentService.getCommentById(postId,commentId);
        return new ResponseEntity<>(commentDto,HttpStatus.OK);
    }
    @PutMapping("/posts/{postId}/comments/{id}")//annotate handler method to handle HTTP PUT request
    public ResponseEntity<CommentDto> updateComment(@Valid @RequestBody CommentDto commentDto,
                                                 @PathVariable(value = "postId")Long postId,
                                                 @PathVariable(value = "id")long commentId){
        CommentDto updatedComment = commentService.updateComment(postId,commentId,commentDto);
        return new ResponseEntity<>(updatedComment,HttpStatus.OK);
    }
    @DeleteMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<String> deleteComment( @PathVariable(name = "postId")Long postId,
                                                 @PathVariable(name = "id")Long commentId) {
        commentService.deleteComment(postId,commentId);
        return new ResponseEntity<>("Comment deleted successfully.",HttpStatus.OK);
    }

}
