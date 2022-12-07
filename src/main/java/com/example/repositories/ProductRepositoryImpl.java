package com.example.repositories;

import com.example.models.Product;
import com.example.models.ProductCategory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepositoryImpl implements ProductRepository
{
    @Autowired
    public ProductRepositoryImpl(MongoTemplate mongoTemplate)
    {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public List<Product> findAll()
    {
        return mongoTemplate.findAll(Product.class);
    }

    @Override
    public List<Product> findByName(String name)
    {
        Query query = new Query().addCriteria(Criteria.where("name").regex(name, "imx"));

        return mongoTemplate.find(query, Product.class);
    }

    @Override
    public List<Product> findByCategoryId(Integer id)
    {
        Criteria.where("category");
        Query query = new Query().addCriteria(Criteria.where("category.$id").is(id));

        return mongoTemplate.find(query, Product.class);
    }

    @Override
    public List<Product> findByCategoryName(String name)
    {
        Query query = new Query().addCriteria(Criteria.where("name").regex(name));
        ProductCategory category = mongoTemplate.findOne(query, ProductCategory.class);

        return findByCategoryId(category.getId());
    }

    @Override
    public void addProduct(Product product)
    {
        mongoTemplate.save(product);
    }

    @Override
    public int maxId()
    {
        Query query = new Query().limit(1).with(Sort.by(Sort.Direction.DESC, "id"));
        Optional<Product> product = Optional.ofNullable(mongoTemplate.findOne(query, Product.class));

        return product.isPresent() ? product.get().getId() : -1;
    }

    private final MongoTemplate mongoTemplate;
}
