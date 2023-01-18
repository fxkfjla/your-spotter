package com.example.repositories;

import com.example.models.Comment;

import java.util.List;

public interface CommentRepository
{
    /**
     * @param id product id
     * @return list of comments that fits passed id
     */
    List<Comment> findByProductId(int id);
    /**
     * adds new comment to database
     * @param comment
     */
    void addComment(Comment comment);
}
