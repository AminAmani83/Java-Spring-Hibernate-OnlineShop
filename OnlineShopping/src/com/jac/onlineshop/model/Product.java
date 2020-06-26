package com.jac.onlineshop.model;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "product")
public class Product implements Serializable {

	// Variables
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name = "name", nullable = false, unique = false)
	private String name;
	
	@Column(name = "description", nullable = true, unique = false)	
	private String description;
	
	@Column(name = "image", nullable = false, unique = false)	
	private String imageLink;

	@Column(name = "unit_price", nullable = false, unique = false)	
	private Double unitPrice;

	@OneToMany(
        mappedBy = "product",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
	private Set<CartItem> cartItems;
	
	
	// Constructors
	
	public Product() {
	}
	
	public Product(String name, String description, String imageLink, Double unitPrice) {
		this.name = name;
		this.description = description;
		this.imageLink = imageLink;
		this.unitPrice = unitPrice;
	}

	
	// Getters & Setters
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImageLink() {
		return imageLink;
	}

	public void setImageLink(String imageLink) {
		this.imageLink = imageLink;
	}

	public Double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Set<CartItem> getCartItems() {
		return cartItems;
	}

	public void setCartItems(Set<CartItem> cartItems) {
		this.cartItems = cartItems;
	}
	
	
	// Double-Sided Setters
	public void addCartItem(CartItem cartItem) { // One-to-Many
		this.cartItems.add(cartItem);
		cartItem.setProduct(this);
	}	

	public void removeCartItem(CartItem cartItem) { // One-to-Many
		this.cartItems.remove(cartItem);
		cartItem.setProduct(null);
	}	
	
	
	// Equals & HashCode
	
	@Override
	public int hashCode() {
		return Objects.hash(description, imageLink, name, unitPrice);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		return Objects.equals(description, other.description) && Objects.equals(imageLink, other.imageLink)
				&& Objects.equals(name, other.name) && Objects.equals(unitPrice, other.unitPrice);
	}

}
