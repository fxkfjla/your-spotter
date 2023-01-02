package com.example.repositories;

import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.example.models.Cart;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Repository
public class CartRepositoryImpl implements CartRepository
{
    @Override
    public Cart findByUserId(int userId)
    {
        Query query = new Query().addCriteria(Criteria.where("userId").is(userId));
        return mongoTemplate.findOne(query, Cart.class);
    }

    @Override
    public Cart findByCartId(Integer cartId)
    {
        Query query = new Query().addCriteria(Criteria.where("_id").is(cartId));
        return mongoTemplate.findOne(query, Cart.class);
    }

    @Override
    public void save(Cart cart)
    {
        mongoTemplate.save(cart);
    }

    @Override
    public void remove(Cart cart)
    {
        mongoTemplate.remove(cart);
    }

    @Override
    public Integer nextId()
    {
        Query query = new Query().limit(1).with(Sort.by(Sort.Direction.DESC, "_id"));
        Optional<Cart> cart = Optional.ofNullable(mongoTemplate.findOne(query, Cart.class));

        return cart.isPresent() ? cart.get().getId() + 1 : 0;
    }

    private MongoTemplate mongoTemplate;
}
