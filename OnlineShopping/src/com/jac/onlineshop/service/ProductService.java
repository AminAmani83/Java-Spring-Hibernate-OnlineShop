package com.jac.onlineshop.service;

import java.util.List;

import com.jac.onlineshop.model.Product;

public interface ProductService {
	List<Product> getAllProducts();
	boolean deleteProduct(int productId);
	boolean deleteProduct(Product product); // Overloaded
	boolean addProduct(Product product);
	boolean updateProduct(Product product);
	Product getProductById(int productId);
}
