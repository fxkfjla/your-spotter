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
    /**
     * 
     * @param name 
     * @param imageUrl url to visual representation of a category
     */
    public ProductCategory(String name, String imageUrl)
    {
        this.name = name;
        this.imageUrl = imageUrl;
    }

    @Id
    private Integer id;
    @Field
    private String name;
    @Field
    private String imageUrl;
}