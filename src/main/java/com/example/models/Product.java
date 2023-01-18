package com.example.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.NoArgsConstructor;

@Document("products")
@ToString
@Getter
@Setter
@NoArgsConstructor
public class Product
{
    /**
     * 
     * @param name of the product
     * @param amount
     * @param price
     * @param imageUrl url visual representation of a product 
     * @param category 
     */
    public Product(String name, Integer amount, Float price, String imageUrl, ProductCategory category)
    {
        this.name = name;
        this.amount = amount;
        this.price = price;
        this.imageUrl = imageUrl;
        this.category = category;
    }

    @Id
    private int id;
    @Field
    private String name;
    @Field
    private Integer amount;
    @Field
    private Float price;
    @Field
    private String imageUrl;
    @Field
    @DBRef
    // every product refrences category that it belongs to
    private ProductCategory category;
}