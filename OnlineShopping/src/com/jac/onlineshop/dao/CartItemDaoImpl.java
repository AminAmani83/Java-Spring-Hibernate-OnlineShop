package com.jac.onlineshop.dao;

import java.util.Optional;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jac.onlineshop.model.CartItem;
import com.jac.onlineshop.model.User;

@Repository
public class CartItemDaoImpl implements CartItemDao {

	// Variables
	@Autowired
	private SessionFactory sessionFactory;

	// Constructors
	public CartItemDaoImpl() {
	}

	// Methods
	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public Set<CartItem> getAllCartItems(User user) { // We are using Eager Fetching for CartItems
		// Option 1. Simplest Way: Only works if in the model we have: @ManyToMany(fetch
		// = FetchType.EAGER) or if we use @Transactional
		return user.getCartItems();

		// Option 2.
		// String hql = "FROM CartItem AS c WHERE c.user.id = :user_id";
		// Query<CartItem> query = getSession().createQuery(hql, CartItem.class);
		// query.setParameter("user_id", user.getId());
		// return query.list();
	}

	@Override
	public boolean deleteCartItem(int cartItemId) {
		CartItem cartItem = this.getCartItemById(cartItemId);
		return deleteCartItem(cartItem);
	}

	@Override // overloaded
	public boolean deleteCartItem(CartItem cartItem) {
		// remove it from the user cartItems set
		cartItem.getUser().removeCartItem(cartItem);
		// delete it altogether
		getSession().delete(cartItem);
		return true;
	}

	@Override
	public boolean addCartItem(CartItem cartItem) { // We are using Eager Fetching for CartItems
		// if the product is already in the cart, update the quantity in the cart item
		Optional<CartItem> previousCartItem = cartItem.getUser().getCartItems().stream()
				.filter(c -> c.getProduct().getId() == cartItem.getProduct().getId()).findAny();
		if (previousCartItem.isPresent()) { // update the previous cart item
			previousCartItem.get().setQuantity(previousCartItem.get().getQuantity() + cartItem.getQuantity());
			getSession().update(previousCartItem.get());
		} else { // save as a new cart item
			getSession().save(cartItem);
		}
		return true;
	}

	@Override
	public boolean updateCartItem(CartItem cartItem) {
		getSession().update(cartItem);
		return true;
	}

	@Override
	public CartItem getCartItemById(int cartItemId) {
		return getSession().get(CartItem.class, cartItemId);
	}

}
