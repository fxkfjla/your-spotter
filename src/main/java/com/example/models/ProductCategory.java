package com.example.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Document("product_categories")
public class ProductCategory
{
    public ProductCategory(String name, String image)
    {
        this.name = name;
        this.image = image;
    }

    @Id
    private Integer id;
    @Field
    private String name;
    @Field
    private String image;
}