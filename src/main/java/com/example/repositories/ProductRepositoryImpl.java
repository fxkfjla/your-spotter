package com.example.repositories;

import com.example.models.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductRepositoryImpl implements ProductRepository
{
    @Autowired
    public ProductRepositoryImpl(MongoTemplate mongoTemplate)
    {
        this.mongoTemplate = mongoTemplate;
    }

    public List<Product> findAll()
    {
        List<Product> products = mongoTemplate.findAll(Product.class);
        return products;
    }

    public List<Product> findByName(String name)
    {
        Query query = new Query();
        // query.addCriteria(Criteria.where("name").is(name));
        query.addCriteria(Criteria.where("name").regex(name, "imx"));
        List<Product> products = mongoTemplate.find(query, Product.class);

        return products;
    }

    private final MongoTemplate mongoTemplate;
}
