package com.example.repositories;

import com.example.models.Cart;

public interface CartRepository
{
    public Cart findByUserId(int userId);    
    public Cart findByCartId(Integer cartId);    
    public void save(Cart cart);
    public void remove(Cart cart);
    public Integer nextId();
}