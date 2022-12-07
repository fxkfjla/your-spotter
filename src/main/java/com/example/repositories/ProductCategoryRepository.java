package com.example.repositories;

import com.example.models.ProductCategory;

import java.util.List;

public interface ProductCategoryRepository
{
    public List<ProductCategory> findAll();
    public void addCategory(ProductCategory category);
    public Integer maxId();
}