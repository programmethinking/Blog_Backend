package com.springboot.blog.service;

import com.springboot.blog.payload.CommentDto;

import java.util.List;

public interface CommentService {
    CommentDto createComment(long postId, CommentDto commentDto);

    List<CommentDto> getCommentsByPostId(long postId);//筛选一个POST下面的所有评论

    CommentDto getCommentById(Long postId, Long commentId);//筛选出一个评论,get from url dynamically, so need to design it as method argument,so use Long rather than long

    CommentDto updateComment(Long postId, long commentId, CommentDto commentRequest);
    void deleteComment(Long postId,Long commentId);
}
