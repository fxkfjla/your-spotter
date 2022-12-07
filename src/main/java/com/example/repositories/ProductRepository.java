package com.example.repositories;

import com.example.models.Product;

import java.util.List;

public interface ProductRepository
{
    public List<Product> findAll();
    public List<Product> findByName(String name);
    public List<Product> findByCategoryId(Integer id);
    public List<Product> findByCategoryName(String name);
    public void addProduct(Product product);
    public int maxId();
}