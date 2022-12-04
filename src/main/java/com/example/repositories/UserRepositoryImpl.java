package com.example.repositories;

import com.example.models.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository
{
    @Autowired
    public UserRepositoryImpl(MongoTemplate mongoTemplate)
    {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public List<User> findAll()
    {
        return mongoTemplate.findAll(User.class);
    }

    @Override
    public Optional<User> findByEmail(String email)
    {
        Query query = new Query();
        query.addCriteria(Criteria.where("email").regex(email));

        return Optional.ofNullable(mongoTemplate.findOne(query, User.class));
    }
    
    @Override
    public Long maxId()
    {
        Query query = new Query().limit(1).with(Sort.by(Sort.Direction.DESC, "id"));
        Optional<User> user = Optional.ofNullable(mongoTemplate.findOne(query, User.class));

        return user.isPresent() ? user.get().getId() : -1L;
    }

    @Override
    public void save(User user)
    {
        mongoTemplate.save(user);
    }

    private final MongoTemplate mongoTemplate;
}
