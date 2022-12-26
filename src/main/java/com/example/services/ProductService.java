package com.example.services;

import com.example.models.Product;
import com.example.repositories.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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

    public List<Product> getAll(Optional<String> order, Optional<String> by)
    {
        Sort.Direction sortDir = parseSortString(order);
        String sortBy = by.orElse("_id");

        return productRepository.findAll(sortDir, sortBy);
    }

    public List<Product> getByName(String name, Optional<String> order, Optional<String> by)
    {
        Sort.Direction sortDir = parseSortString(order);
        String sortBy = by.orElse("_id");

        return productRepository.findByName(name, sortDir, sortBy);
    }

    public List<Product> getByCategoryId(Integer id, Optional<String> order, Optional<String> by)
    {
        Sort.Direction sortDir = parseSortString(order);
        String sortBy = by.orElse("_id");

        return productRepository.findByCategoryId(id, sortDir, sortBy);
    }

    public List<Product> getByCategoryName(String name, Optional<String> order, Optional<String> by)
    {
        Sort.Direction sortDir = parseSortString(order);
        String sortBy = by.orElse("_id");

        return productRepository.findByCategoryName(name, sortDir, sortBy);
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

    private Sort.Direction parseSortString(Optional<String> order)
    {
        Sort.Direction sortDir = Sort.DEFAULT_DIRECTION;

        if(!order.isEmpty())
            sortDir = order.get().equals("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC;

        return sortDir;
    }

    private final ProductRepository productRepository;
}