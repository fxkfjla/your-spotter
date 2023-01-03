package com.example.repositories;

import com.example.models.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
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
        Query query = new Query().addCriteria(Criteria.where("email").regex(email));

        return Optional.ofNullable(mongoTemplate.findOne(query, User.class));
    }

    @Override
    public Optional<User> findById(int id)
    {
        Query query = new Query().addCriteria(Criteria.where("_id").is(id));

        return Optional.ofNullable(mongoTemplate.findOne(query, User.class));
    }

    @Override
    public Optional<User> findBySessionId(String id)
    {
        Query query = new Query().addCriteria(Criteria.where("sessionId").is(id));

        return Optional.ofNullable(mongoTemplate.findOne(query, User.class));
    }

    @Override
    public void enableUser(int id)
    {
        Query query = new Query().addCriteria(Criteria.where("_id").is(id));
        Update update = new Update().set("enabled", true);

        mongoTemplate.updateFirst(query, update, User.class);
    }

    @Override
    public int maxId()
    {
        Query query = new Query().limit(1).with(Sort.by(Sort.Direction.DESC, "id"));
        Optional<User> user = Optional.ofNullable(mongoTemplate.findOne(query, User.class));

        return user.isPresent() ? user.get().getId() : -1;
    }

    @Override
    public void save(User user)
    {
        mongoTemplate.save(user);
    }

    @Override
    public void updatePassword(String password, String email)
    {
        Query query = new Query().addCriteria(Criteria.where("email").is(email));
        Update update = new Update().set("password", password);
        mongoTemplate.updateFirst(query, update, User.class);
    }

    private final MongoTemplate mongoTemplate;
}
