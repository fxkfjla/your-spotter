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
    public Product(String name, Integer amount, Float price, ProductCategory category)
    {
        this.name = name;
        this.amount = amount;
        this.price = price;
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
    @DBRef
    private ProductCategory category;
}