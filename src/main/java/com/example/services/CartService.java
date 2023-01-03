package com.example.services;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.example.models.Cart;
import com.example.models.Product;
import com.example.models.User;
import com.example.repositories.CartRepository;
import com.example.repositories.UserRepository;
import com.example.utility.LoyalityPoints;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CartService
{
   public Cart getCartByUserId(int userId, HttpServletRequest request)
   {
      Cart cart = null;

      if(userRepository.findById(userId).isPresent()) 
         cart = cartRepository.findByUserId(userId);
      else
      {
         HttpSession session = request.getSession();
         Integer cartId = (Integer)session.getAttribute("cartId");

         if(cartId != null) 
            cart = cartRepository.findByCartId(cartId);
      }

      return cart;
   }

   public void checkout(int userId, HttpServletRequest request)
   {
      Cart cart = getCartByUserId(userId, request);
      Optional<User> user = userRepository.findById(userId);

      if(cart != null)
      {
         if(user.isPresent())
         {
            User _user = user.get();

            int earnedLoyalityPoints = LoyalityPoints.calculate(cart.getTotalPrice());
            _user.setLoyalityPoints(_user.getLoyalityPoints() + earnedLoyalityPoints);

            userRepository.save(_user);
         }
          
         cartRepository.remove(cart);
      }
   }

   public void addProduct(int userId, Product product, HttpServletRequest request)
   {
      Cart cart = getCartByUserId(userId, request);

      if(cart == null)
      {
         if(!userRepository.findById(userId).isPresent())
            request.getSession().setAttribute("cartId", cartRepository.nextId());

         cart = new Cart(userId);
         cart.setId(cartRepository.nextId());
      }

      cart.addProduct(product);
      cartRepository.save(cart);
   }

   public void removeProduct(int userId, Product product, HttpServletRequest request)
   {
      Cart cart = getCartByUserId(userId, request);
      cart.removeProduct(product);
      cartRepository.save(cart);
   }

   public void clear(int userId, HttpServletRequest request)
   {
      Cart cart = getCartByUserId(userId, request);
      cart.clear();
      cartRepository.save(cart);
   }

   public void remove(int userId, HttpServletRequest request)
   {
      Cart cart = getCartByUserId(userId, request);

      // get rid of cookies that hold other carts
      request.getSession().invalidate();

      cartRepository.remove(cart);
   }

   private CartRepository cartRepository;
   private UserRepository userRepository;
}