package com.example.services;

import com.example.models.Product;
import com.example.repositories.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService
{
    @Autowired
    public ProductService(ProductRepository productRepository)
    {
        this.productRepository = productRepository;
    }

    public List<Product> getAll()
    {
        return productRepository.findAll();
    }

    public List<Product> getByName(String name)
    {
        return productRepository.findByName(name);
    }

    public void addProduct(Product product)
    {
        product.setId(productRepository.maxId() + 1);
        productRepository.addProduct(product);
    }

    public void addProducts(List<Product> products)
    {
        for(Product product : products)
        {
            addProduct(product);
        }
    }

    private final ProductRepository productRepository;
}