package com.jac.onlineshop.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "user")
public class User implements Serializable{
	
	// Variables
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name = "username", nullable = false, unique = true)
	private String username;
	
	@Column(name = "password", nullable = false, unique = false)
	private String password;
	
	@Column(name = "email", nullable = false, unique = true)
	private String email;
	
	@Column(name = "first_name", nullable = false, unique = false)
	private String firstName;
	
	@Column(name = "middle_name", nullable = true, unique = false)
	private String middleName;
	
	@Column(name = "last_name", nullable = false, unique = false)
	private String lastName;
	
	@Column(name = "gender", length = 1, nullable = false, unique = false)
	@Enumerated(EnumType.STRING) // ENUM
	private UserGender gender;
	
	@Column(name = "phone", nullable = true, unique = false)
	private String phone;

	@OneToMany(
        mappedBy = "user",
        fetch = FetchType.EAGER,
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
	private Set<CartItem> cartItems = new HashSet<>();
	
	@ManyToMany(
		cascade = {
	        CascadeType.PERSIST,
	        CascadeType.MERGE
		}
	)
    @JoinTable(
        name = "user_address", // the middle table in the DB, joining the two main tables
        joinColumns = { @JoinColumn(name = "user_id") }, 
        inverseJoinColumns = { @JoinColumn(name = "address_id") }
    )
	private Set<Address> addresses = new HashSet<>();
	
	
	// Constructors
	
	public User() {
	}
	
	public User(String username, String password, String email, String firstName, String middleName, String lastName,
			UserGender gender, String phone) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.gender = gender;
		this.phone = phone;
	}

	
	// Getters & Setters
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public UserGender getGender() {
		return gender;
	}

	public void setGender(UserGender gender) {
		this.gender = gender;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Set<CartItem> getCartItems() {
		return cartItems;
	}

	public void setCartItems(Set<CartItem> cartItems) {
		this.cartItems = cartItems;
	}
	
	public Set<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(Set<Address> addresses) {
		this.addresses = addresses;
	}
	
	
	// Double-Sided Setters
	
	public void addCartItem(CartItem cartItem) { // One-to-Many
		this.cartItems.add(cartItem);
		cartItem.setUser(this);
	}	

	public void removeCartItem(CartItem cartItem) { // One-to-Many
		this.cartItems.remove(cartItem);
		cartItem.setUser(null);
	}	

	public void addAddress(Address address) { // Many-to-Many
		this.addresses.add(address);
		address.getUsers().add(this);
	}	

	public void removeAddress(Address address) { // Many-to-Many
		this.addresses.remove(address);
		address.getUsers().remove(this);
	}

	public void removeAllRelations() { // before removing the user do this
		new HashSet<>(this.addresses).forEach(a -> this.removeAddress(a));
		new HashSet<>(this.cartItems).forEach(c -> this.removeCartItem(c));
	}
	
	// Equals & HashCode
	
	@Override
	public int hashCode() {
		return Objects.hash(email, firstName, gender, lastName, middleName, password, phone, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(email, other.email) && Objects.equals(firstName, other.firstName)
				&& gender == other.gender && Objects.equals(lastName, other.lastName)
				&& Objects.equals(middleName, other.middleName) && Objects.equals(password, other.password)
				&& Objects.equals(phone, other.phone) && Objects.equals(username, other.username);
	}	
	
}
