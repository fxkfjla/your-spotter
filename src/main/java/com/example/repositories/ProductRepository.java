package com.example.repositories;

import com.example.models.Product;

import org.springframework.data.domain.Sort;

import java.util.List;

public interface ProductRepository
{
    public List<Product> findAll(Sort.Direction order, String by);
    public List<Product> findByName(String name, Sort.Direction order, String by);
    public List<Product> findByCategoryId(Integer id, Sort.Direction order, String by);
    public List<Product> findByCategoryName(String name, Sort.Direction order, String by);
    public void addProduct(Product product);
    public int maxId();
}