package com.jac.onlineshop.dao;

import java.util.Set;

import com.jac.onlineshop.model.CartItem;
import com.jac.onlineshop.model.User;

public interface CartItemDao {
	Set<CartItem> getAllCartItems(User User);
	boolean deleteCartItem(int cartItemId);
	boolean deleteCartItem(CartItem cartItem); // overloaded
	boolean addCartItem(CartItem cartItem);
	boolean updateCartItem(CartItem cartItem);
	CartItem getCartItemById(int cartItemId);
}
