package com.example.controllers;

import com.example.models.Cart;
import com.example.models.Product;
import com.example.services.CartService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/cart")
@AllArgsConstructor
public class CartController
{
    @GetMapping
    public Cart getCartByUserId(@RequestParam("userId") int userId, HttpServletRequest request)
    {
        return cartService.getCartByUserId(userId, request);
    }

    @PostMapping("addProduct")
    public void addProduct(@RequestParam("userId") int userId, @RequestBody Product product, HttpServletRequest request)
    {
        cartService.addProduct(userId, product, request);
    }

    @DeleteMapping("removeProduct")
    public void removeProduct(@RequestParam("userId") int userId, @RequestBody Product product, HttpServletRequest request)
    {
        cartService.removeProduct(userId, product, request);
    }

    @DeleteMapping("clear")
    public void clear(@RequestParam("userId") int userId, HttpServletRequest request)
    {
        cartService.clear(userId, request);
    }

    @DeleteMapping("remove")
    public void remove(@RequestParam("userId") int userId, HttpServletRequest request)
    {
        cartService.remove(userId, request);
    }

    private CartService cartService;
}