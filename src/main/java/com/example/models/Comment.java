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
    public Comment(String comment, Product product)
    {
        this.comment = comment;
        this.product = product;
    }

    @Field
    private String comment;
    @Field
    @DBRef
    private Product product;
}
