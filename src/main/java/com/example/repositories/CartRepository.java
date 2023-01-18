package com.example.repositories;

import com.example.models.Cart;

public interface CartRepository
{
    /**
     * @param userId
     * @return cart that fits passed id
     */
    public Cart findByUserId(int userId);    
    /**
     * @param userId
     * @return cart that fits passed id
     */
    public Cart findByCartId(Integer cartId);    
    /**
     * saves new cart to database
     * @param cart
     */
    public void save(Cart cart);
    /**
     * removes passed cart from database
     * @param cart
     */
    public void remove(Cart cart);
    /**
     * @return next highest id + 1 of cart collection
     */
    public Integer nextId();
}