package com.example.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Document("carts")
@ToString
@Getter
@Setter
public class Cart
{
    public Cart()
    {
        userId = null;
        products = new ArrayList<Product>();
        totalPrice = 0;
    }

    public Cart(int userId)
    {
        this.userId = userId;
        products = new ArrayList<Product>();
        totalPrice = 0;
    }

    public void addProduct(Product product)
    {
        products.add(product);
        totalPrice += product.getPrice();
    }

    public void removeProduct(Product product)
    {
        for(Product _product : products)
        {
            if(_product.getId() == product.getId())
            {
                totalPrice -= _product.getPrice();
                products.remove(_product);
                break;
            }
        }
    }

    public void clear()
    {
        products.clear();
        totalPrice = 0;
    }

    @Id
    private Integer id;
    @Field
    private Integer userId;
    @Field
    private List<Product> products;
    @Field
    private double totalPrice;
}
