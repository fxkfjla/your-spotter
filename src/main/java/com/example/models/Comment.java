package com.example.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document("comments")
@Getter
@Setter
@NoArgsConstructor
public class Comment
{
    /**
     * 
     * @param comment actual comment content
     * @param product reference to product
     * @param userEmail
     * @param data time the comment is posted
     */
    public Comment(String comment, Product product, String userEmail, String data)
    {
        this.comment = comment;
        this.product = product;
        this.userEmail = userEmail;
        this.data = data;
    }

    @Field
    private String comment;

    @Field
    @DBRef
    // every comment refrences product that it belongs to 
    private Product product;

    @Field
    private String userEmail;

    @Field
    private String data;
}
