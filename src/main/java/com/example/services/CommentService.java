package com.example.services;

import com.example.models.Comment;
import com.example.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService
{
    @Autowired
    public CommentService(CommentRepository commentRepository)
    {
        this.commentRepository = commentRepository;
    }

    public List<Comment> getByProductId(int id)
    {
        return commentRepository.findByProductId(id);
    }

    public void addComment(Comment comment)
    {
        if(!comment.getComment().isEmpty())
            commentRepository.addComment(comment);
    }

    private final CommentRepository commentRepository;
}
