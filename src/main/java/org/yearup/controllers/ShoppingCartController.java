package org.yearup.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.yearup.data.ProductDao;
import org.yearup.data.ShoppingCartDao;
import org.yearup.data.UserDao;
import org.yearup.models.ShoppingCart;
import org.yearup.models.User;

import java.security.Principal;
import java.sql.SQLException;

//@RestController // convert this class to a REST controller
//@RequestMapping("/cart")
//@PreAuthorize("hasRole('ROLE_USER')") // only logged in users should have access to these actions
//public class ShoppingCartController
//{
//    // a shopping cart requires
//    private ShoppingCartDao shoppingCartDao;
//    private UserDao userDao;
//    private ProductDao productDao;
//
//    public ShoppingCartController(ShoppingCartDao shoppingCartDao, UserDao userDao, ProductDao productDao) {
//        this.shoppingCartDao = shoppingCartDao;
//        this.userDao = userDao;
//        this.productDao = productDao;
//    }
//
//    // each method in this controller requires a Principal object as a parameter
//    public ShoppingCart getCart(Principal principal) {
//        try
//        {
//            // get the currently logged in username
//            String userName = principal.getName();
//            // find database user by userId
//            User user = userDao.getByUserName(userName);
//            int userId = user.getId();
//
//            // use the shoppingcartDao to get all items in the cart and return the cart
//            return shoppingCartDao.getByUserId(userId);
//        }
//        catch(Exception e)
//        {
//            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Oops... our bad.");
//        }
//    }
//
//  @PostMapping("/products/15")  // add a POST method to add a product to the cart - the url should be https://localhost:8080/cart/products/15
//  public void addProduct(@PathVariable int productId, Principal principal) {
//        try{
//            String username = principal.getName();
//            User user = userDao.getByUserName(username);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//  }
//
//
//@PutMapping("/products/15")    // add a PUT method to update an existing product in the cart - the url should be https://localhost:8080/cart/products/15
//    // the BODY should be a ShoppingCartItem - quantity is the only value that will be updated
//public void updateQuantity(@PathVariable int userId, int productId, int quantity, Principal principal){
//
//}
//
//@DeleteMapping
//public void clearCart(@PathVariable int userId, Principal principal){
//
//}


//}
