package com.example.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.example.models.ProductCategory;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Repository
public class ProductCategoryRepositoryImpl implements ProductCategoryRepository
{
    @Override
    public List<ProductCategory> findAll()
    {
        return mongoTemplate.findAll(ProductCategory.class);
    }

    @Override
    public void addCategory(ProductCategory category)
    {
        mongoTemplate.save(category);
    }

    @Override
    public Integer maxId()
    {
        Query query = new Query().limit(1).with(Sort.by(Sort.Direction.DESC, "id"));
        Optional<ProductCategory> category = Optional.ofNullable(mongoTemplate.findOne(query, ProductCategory.class));

        return category.isPresent() ? category.get().getId() : -1;
    }
    
    private final MongoTemplate mongoTemplate;
}