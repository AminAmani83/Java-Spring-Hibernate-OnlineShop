package com.jac.onlineshop.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "cart")
public class CartItem implements Serializable{

	// Variables
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "user_id") // foreign key in this table
    private User user;

	@ManyToOne
	@JoinColumn(name = "product_id") // foreign key in this table
    private Product product;
	
	@Column(name = "quantity", nullable = false, unique = false)
	private int quantity;

	
	// Constructors
	
	public CartItem() {
	}

	public CartItem(User user, Product product, int quantity) {
		this.user = user;
		this.product = product;
		this.quantity = quantity;
	}

	
	// Getters & Setters
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	
	// Equals & HashCode
	
	@Override
	public int hashCode() {
		return Objects.hash(product, quantity, user);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CartItem other = (CartItem) obj;
		return Objects.equals(product, other.product) && quantity == other.quantity && Objects.equals(user, other.user);
	}

}
