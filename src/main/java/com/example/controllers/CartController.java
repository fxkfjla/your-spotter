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
    /**
     * @param userId
     * @param request http servlet request, used to find cart if userId is not present
     * @return cart for passed user or if not present, request is used to get current session and corresponding cart
     */
    @GetMapping
    public Cart getCartByUserId(@RequestParam("userId") int userId, HttpServletRequest request)
    {
        return cartService.getCartByUserId(userId, request);
    }

    /**
     * user buys products from cart 
     * @param userId
     * @param request http servlet request, used to find cart if userId is not present
     */
    @GetMapping("checkout")
    public void checkout(@RequestParam("userId") int userId, HttpServletRequest request)
    {
        cartService.checkout(userId, request);
    }

    /**
     * adds product to cart 
     * @param userId
     * @param product
     * @param request http servlet request, used to find cart if userId is not present
     */
    @PostMapping("addProduct")
    public void addProduct(@RequestParam("userId") int userId, @RequestBody Product product, HttpServletRequest request)
    {
        cartService.addProduct(userId, product, request);
    }

    /**
     * removes product from cart 
     * @param userId
     * @param product
     * @param request http servlet request, used to find cart if userId is not present
     */
    @DeleteMapping("removeProduct")
    public void removeProduct(@RequestParam("userId") int userId, @RequestBody Product product, HttpServletRequest request)
    {
        cartService.removeProduct(userId, product, request);
    }

    /**
     * removes products from cart 
     * @param userId
     * @param product
     * @param request http servlet request, used to find cart if userId is not present
     */
    @DeleteMapping("clear")
    public void clear(@RequestParam("userId") int userId, HttpServletRequest request)
    {
        cartService.clear(userId, request);
    }

    /**
     * removes cart from database
     * @param userId
     * @param request http servlet request, used to find cart if userId is not present
     */
    @DeleteMapping("remove")
    public void remove(@RequestParam("userId") int userId, HttpServletRequest request)
    {
        cartService.remove(userId, request);
    }

    private CartService cartService;
}