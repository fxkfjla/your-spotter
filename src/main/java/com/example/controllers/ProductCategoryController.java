package com.example.controllers;

import com.example.models.ProductCategory;
import com.example.services.ProductCategoryService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
@RequestMapping("/products/categories")
@RestController
public class ProductCategoryController
{
    /**
     * @return list of all product categories
     */
    @GetMapping(path = "all")
    public List<ProductCategory> getAll()
    {
        return categoryService.getAll();
    }

    /**
     * adds new category to database
     * @param category
     */
    @ResponseBody
    @PostMapping(path = "addOne")
    public void addProduct(@RequestBody ProductCategory category)
    {
        categoryService.addCategory(category);
    }

    /**
     * adds new categories to database
     * @param categories
     */
    @ResponseBody
    @PostMapping(path = "addMany")
    public void addProducts(@RequestBody List<ProductCategory> categories)
    {
        categoryService.addCategories(categories);
    }
   
    private final ProductCategoryService categoryService;
}