package com.example.repositories;

import com.example.models.Product;

import org.springframework.data.domain.Sort;

import java.util.List;

public interface ProductRepository
{
    /**
     * @param order ascending / descending
     * @param by attribute to sort by
     * @return list of all products
     */
    public List<Product> findAll(Sort.Direction order, String by);
    /**
     * @param name product name
     * @param order ascending / descending
     * @param by attribute to sort by
     * @return list of products that fit name
     */
    public List<Product> findByName(String name, Sort.Direction order, String by);
    /**
     * 
     * @param id category id
     * @param order ascending / descending
     * @param by attribute to sort by
     * @return list of products that fit category id
     */
    public List<Product> findByCategoryId(Integer id, Sort.Direction order, String by);
    /**
     * @param name category name
     * @param order ascending / descending
     * @param by attribute to sort by
     * @return list of products that fit category name
     */
    public List<Product> findByCategoryName(String name, Sort.Direction order, String by);
    /**
     * adds new product to database
     * @param product
     */
    public void addProduct(Product product);
    /**
     * @return highest id from product collection
     */
    public int maxId();
}