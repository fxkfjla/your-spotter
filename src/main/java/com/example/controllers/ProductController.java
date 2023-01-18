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

    /**
     * @param order ascending / descending
     * @param by attribute to sort by
     * @return list of all products
     */
    @GetMapping(path = "all")
    public List<Product> getAll(@RequestParam("order") Optional<String> order, @RequestParam("by") Optional<String> by)
    {
        return productService.getAll(order, by);
    }

    /**
     * @param name product name
     * @param order ascending / descending
     * @param by attribute to sort by
     * @return list of products that fit name
     */
    @GetMapping(path = "search")
    public List<Product> getByName
    (
        @RequestParam("name") String name, 
        @RequestParam("order") Optional<String> order, @RequestParam("by") Optional<String> by
    )
    {
        return productService.getByName(name, order, by);
    }

    /**
     * 
     * @param id category id
     * @param order ascending / descending
     * @param by attribute to sort by
     * @return list of products that fit category id
     */
    @GetMapping(path = "categoryId")
    public List<Product> getByCategoryId
    (
        @RequestParam("id") Integer id,
        @RequestParam("order") Optional<String> order, @RequestParam("by") Optional<String> by
    )
    {
        return productService.getByCategoryId(id, order, by);
    }

    /**
     * @param name category name
     * @param order ascending / descending
     * @param by attribute to sort by
     * @return list of products that fit category name
     */
    @GetMapping(path = "categoryName")
    public List<Product> getByCategoryName
    (
        @RequestParam("name") String name,
        @RequestParam("order") Optional<String> order, @RequestParam("by") Optional<String> by
    )
    {
        return productService.getByCategoryName(name, order, by);
    }

    /**
     * adds new product to database
     * @param product
     */
    @ResponseBody
    @PostMapping(path = "addOne")
    public void addProduct(@RequestBody Product product)
    {
        productService.addProduct(product);
    }

    /**
     * adds new products to database
     * @param product
     */
    @ResponseBody
    @PostMapping(path = "addMany")
    public void addProducts(@RequestBody List<Product> products)
    {
        productService.addProducts(products);
    }

    private final ProductService productService;
}