package com.jac.onlineshop.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jac.onlineshop.dao.CartItemDao;
import com.jac.onlineshop.model.CartItem;
import com.jac.onlineshop.model.User;

@Service
@Transactional
public class CartItemServiceImpl implements CartItemService {

	// Variables
	@Autowired
	private CartItemDao cartItemDao;
	
	
	// Constructors
	public CartItemServiceImpl() {
	}

	
	// Methods
	@Override
	public Set<CartItem> getAllCartItems(User user) {
		return cartItemDao.getAllCartItems(user);
	}

	@Override
	public boolean deleteCartItem(int cartItemId) {
		return cartItemDao.deleteCartItem(cartItemId);
	}
	
	@Override // Overloaded
	public boolean deleteCartItem(CartItem cartItem) {
		return cartItemDao.deleteCartItem(cartItem);
	}

	@Override
	public boolean addCartItem(CartItem cartItem) {
		return cartItemDao.addCartItem(cartItem);
	}

	@Override
	public boolean updateCartItem(CartItem cartItem) {
		return cartItemDao.updateCartItem(cartItem);
	}

	@Override
	public CartItem getCartItemById(int cartItemId) {
		return cartItemDao.getCartItemById(cartItemId);
	}

}
