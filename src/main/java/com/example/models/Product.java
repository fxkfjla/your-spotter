package com.example.models;

import org.springframework.data.annotation.Id;
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
    public Product(String name, Integer amount, Float price)
    {
        this.name = name;
        this.amount = amount;
        this.price = price;
    }

    @Id
    private String id;
    @Field
    private String name;
    @Field
    private Integer amount;
    @Field
    private Float price;
}