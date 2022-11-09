package com.example.controllers;

import com.example.models.Product;
import com.example.services.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RequestMapping
@RestController
public class ProductController
{
    @Autowired
    public ProductController(ProductService productService)
    {
        this.productService = productService;
    }

    @GetMapping(path = "search")
    public List<Product> getAll()
    {
        return productService.getAll();
    }

    @GetMapping(path = "search={name}")
    public List<Product> getByName(@PathVariable("name") String name)
    {
        return productService.getByName(name);
    }

    private final ProductService productService;
}