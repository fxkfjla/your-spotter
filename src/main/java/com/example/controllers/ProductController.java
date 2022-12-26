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
import java.util.Optional;

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
    public List<Product> getAll(@RequestParam("order") Optional<String> order, @RequestParam("by") Optional<String> by)
    {
        return productService.getAll(order, by);
    }

    @GetMapping(path = "search")
    public List<Product> getByName
    (
        @RequestParam("name") String name, 
        @RequestParam("order") Optional<String> order, @RequestParam("by") Optional<String> by
    )
    {
        return productService.getByName(name, order, by);
    }

    @GetMapping(path = "categoryId")
    public List<Product> getByCategoryId
    (
        @RequestParam("id") Integer id,
        @RequestParam("order") Optional<String> order, @RequestParam("by") Optional<String> by
    )
    {
        return productService.getByCategoryId(id, order, by);
    }

    @GetMapping(path = "categoryName")
    public List<Product> getByCategoryName
    (
        @RequestParam("name") String name,
        @RequestParam("order") Optional<String> order, @RequestParam("by") Optional<String> by
    )
    {
        return productService.getByCategoryName(name, order, by);
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