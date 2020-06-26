package com.jac.onlineshop.service;

import java.util.Set;

import com.jac.onlineshop.model.CartItem;
import com.jac.onlineshop.model.User;

public interface CartItemService {
	Set<CartItem> getAllCartItems(User user);
	boolean deleteCartItem(int cartItemId);
	boolean deleteCartItem(CartItem cartItem); // Overloaded
	boolean addCartItem(CartItem cartItem);
	boolean updateCartItem(CartItem cartItem);
	CartItem getCartItemById(int cartItemId);
}
