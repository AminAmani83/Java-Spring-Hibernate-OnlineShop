package com.jac.onlineshop.dao;

import java.util.List;

import com.jac.onlineshop.model.Product;

public interface ProductDao {
	List<Product> getAllproducts();
	boolean deleteProduct(int productId);
	boolean deleteProduct(Product product); // Overloaded
	boolean addProduct(Product product);
	boolean updateProduct(Product product);
	Product getProductById(int productId);
}
