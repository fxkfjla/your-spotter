package com.example.controllers;

import com.example.models.Comment;
import com.example.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RequestMapping("/comments")
@RestController
public class CommentController
{
    @Autowired
    public CommentController(CommentService commentService)
    {
        this.commentService = commentService;
    }

    /**
     * @param id product id
     * @return list of comments of corresponding product id
     */
    @GetMapping(path = "productId")
    public List<Comment> getByProductId(@RequestParam("id") int id)
    {
        return this.commentService.getByProductId(id);
    }

    /**
     * adds new comment to database
     * @param comment
     */
    @ResponseBody
    @PostMapping(path = "addComment")
    public void addComment(@RequestBody Comment comment)
    {
        this.commentService.addComment(comment);
    }

    private final CommentService commentService;
}
