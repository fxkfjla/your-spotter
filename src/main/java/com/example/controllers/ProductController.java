package com.example.controllers;

import com.example.models.Product;
import com.example.services.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RequestMapping("/products")
@RestController
public class ProductController
{
    @Autowired
    public ProductController(ProductService productService)
    {
        this.productService = productService;
    }

    @GetMapping(path = "all")
    public List<Product> getAll()
    {
        return productService.getAll();
    }

    @GetMapping(path = "search")
    public List<Product> getByName(@RequestParam("name") String name)
    {
        return productService.getByName(name);
    }

    @ResponseBody
    @PostMapping(path = "addOne")
    public void addProduct(@RequestBody Product product)
    {
        productService.addProduct(product);
    }

    @ResponseBody
    @PostMapping(path = "addMany")
    public void addProducts(@RequestBody List<Product> products)
    {
        productService.addProducts(products);
    }

    private final ProductService productService;
}