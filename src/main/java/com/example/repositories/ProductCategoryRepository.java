package com.example.repositories;

import com.example.models.ProductCategory;

import java.util.List;

public interface ProductCategoryRepository
{
    /**
     * @return list of all product categories
     */
    public List<ProductCategory> findAll();
    /**
     * adds new category to database
     * @param category
     */
    public void addCategory(ProductCategory category);
    /**
     * @return highest id from product category collection
     */
    public Integer maxId();
}