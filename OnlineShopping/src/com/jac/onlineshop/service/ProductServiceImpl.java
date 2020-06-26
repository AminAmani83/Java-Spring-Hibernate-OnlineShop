package com.jac.onlineshop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jac.onlineshop.dao.ProductDao;
import com.jac.onlineshop.model.Product;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

	// Variables
	@Autowired
	private ProductDao productDao;
	
	
	// Constructors
	public ProductServiceImpl() {
	}


	// Methods
	@Override
	public List<Product> getAllProducts() {
		return productDao.getAllproducts();
	}

	@Override
	public boolean deleteProduct(int productId) {
		return productDao.deleteProduct(productId);
	}

	@Override // Overloaded
	public boolean deleteProduct(Product product) {
		return productDao.deleteProduct(product);
	}
	
	@Override
	public boolean addProduct(Product product) {
		return productDao.addProduct(product);
	}

	@Override
	public boolean updateProduct(Product product) {
		return productDao.updateProduct(product);
	}

	@Override
	public Product getProductById(int productId) {
		return productDao.getProductById(productId);
	}

}
