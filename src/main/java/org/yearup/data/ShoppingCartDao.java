package org.yearup.data;

import org.yearup.models.ShoppingCart;

import java.sql.SQLException;

public interface ShoppingCartDao {
    ShoppingCart getByUserId(int userId) throws SQLException;
    ShoppingCart addProduct(int userId, int productID) throws SQLException;
    ShoppingCart updateQuantity(int userId, int productId, int quantity) throws SQLException;
    void clearCart(int userId) throws SQLException;
}
