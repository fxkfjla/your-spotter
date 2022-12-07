package com.example.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.models.ProductCategory;
import com.example.repositories.ProductCategoryRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ProductCategoryService
{
    public List<ProductCategory> getAll()
    {
        return categoryRepository.findAll();
    }

    public void addCategory(ProductCategory category)
    {
        category.setId(categoryRepository.maxId() + 1);
        categoryRepository.addCategory(category);
    }

    public void addCategories(List<ProductCategory> categories)
    {
        for(ProductCategory category : categories)
        {
            addCategory(category);
        }
    }
    
    private final ProductCategoryRepository categoryRepository;
}