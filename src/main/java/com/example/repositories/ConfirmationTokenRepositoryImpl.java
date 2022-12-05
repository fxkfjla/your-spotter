package com.example.repositories;

import com.example.models.ConfirmationToken;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.Optional;

@AllArgsConstructor
@Repository
public class ConfirmationTokenRepositoryImpl implements ConfirmationTokenRepository
{
    @Override
    public Optional<ConfirmationToken> findByToken(String token)
    {
        Query query = new Query();
        query.addCriteria(Criteria.where("token").regex(token));

        return Optional.ofNullable(mongoTemplate.findOne(query, ConfirmationToken.class));
    }

    @Override
    public void confirmToken(ConfirmationToken token)
    {
        Query query = new Query().addCriteria(Criteria.where("token").regex(token.getToken()));
        Update update = new Update().set("confirmedAt", LocalDateTime.now());

        mongoTemplate.updateFirst(query, update, ConfirmationToken.class);
    }

    @Override
    public Long maxId()
    {
        Query query = new Query().limit(1).with(Sort.by(Sort.Direction.DESC, "id"));
        Optional<ConfirmationToken> token = Optional.ofNullable(mongoTemplate.findOne(query, ConfirmationToken.class));

        return token.isPresent() ? token.get().getId() : -1L;
    }

    @Override
    public void save(ConfirmationToken token)
    {
        mongoTemplate.save(token);
    }

    private final MongoTemplate mongoTemplate;
}
