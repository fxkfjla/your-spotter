package com.example.repositories;

import com.example.models.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CommentRepositoryImpl implements CommentRepository
{
    @Autowired
    public CommentRepositoryImpl(MongoTemplate mongoTemplate)
    {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public List<Comment> findByProductId(int id)
    {
        Query query = new Query().addCriteria(Criteria.where("product.$id").is(id));

        return mongoTemplate.find(query, Comment.class);
    }

    @Override
    public void addComment(Comment comment)
    {
        mongoTemplate.save(comment);
    }

    private final MongoTemplate mongoTemplate;
}
