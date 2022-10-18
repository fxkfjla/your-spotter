package com.example.controllers;

import com.example.models.Product;
import com.example.services.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RequestMapping(path = "your-spotter/products")
@RestController
public class ProductController
{
    @Autowired
    public ProductController(ProductService productService)
    {
        this.productService = productService;
    }
//    @GetMapping("/your-spotter/products")
    @GetMapping
    public List<Product> getAll()
    {
        return productService.getAll();
    }

//    @GetMapping(path = "id/{id}")
//    public Optional<Product> getBy(@PathVariable("id") String id)
//    {
//        return productService.getBy(id);
//    }

    private final ProductService productService;
}