package com.jac.onlineshop.dao;

import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jac.onlineshop.model.CartItem;
import com.jac.onlineshop.model.Product;

@Repository
public class ProductDaoImpl implements ProductDao {

	// Variables
	@Autowired
	private SessionFactory sessionFactory;
	
	
	// Constructors
	public ProductDaoImpl() {
	}
	
	
	// Methods
	protected Session getSession(){
		return sessionFactory.getCurrentSession();
	}
		
	@Override
	public List<Product> getAllproducts() {
		return getSession().createQuery("from Product", Product.class).list();
	}

	@Override
	public boolean deleteProduct(int productId) {
		Product product = this.getProductById(productId);
		return this.deleteProduct(product);
	}
	
	@Override // Overloaded
	public boolean deleteProduct(Product product) {
		// All cart items related to this product must be removed too
		Set<CartItem> cartItems = product.getCartItems();
		// Remove cart items from User and User from cart items
		// (If cart items remain in user cartItem set, they will be recreated, causing exception)
		cartItems.forEach(c -> c.getUser().removeCartItem(c));
		// Delete Product (No need to remove cart item from product, it is being deleted anyway)
		getSession().delete(product);
		return true;
	}

	@Override
	public boolean addProduct(Product product) {
		getSession().save(product);
		return true;
	}

	@Override
	public boolean updateProduct(Product product) {
		getSession().update(product);
		return true;
	}

	@Override
	public Product getProductById(int productId) {
		return getSession().get(Product.class, productId );
	}

}
