package com.example.services;

import com.example.models.Product;
import com.example.repositories.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Optional<Product> getBy(String id)
    {
        return productRepository.findById(id);
    }

    private final ProductRepository productRepository;
}