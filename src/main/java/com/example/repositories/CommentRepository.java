package com.example.repositories;

import com.example.models.Comment;

import java.util.List;

public interface CommentRepository
{
    List<Comment> findByProductId(int id);
    void addComment(Comment comment);
}
