package com.jac.onlineshop.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "address")
public class Address implements Serializable {

	// Variables
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name = "address_type", nullable = false, unique = false)
	@Enumerated(EnumType.STRING) // ENUM
	private AddressType addressType;
	
	@Column(name = "address_line_1", nullable = false, unique = false)
	private String addressLine1;
	
	@Column(name = "address_line_2", nullable = true, unique = false)
	private String addressLine2;
	
	@Column(name = "city", nullable = false, unique = false)
	private String city;
	
	@Column(name = "province", nullable = false, unique = false)
	private String province;
	
	@Column(name = "country", nullable = false, unique = false)
	private String country;
	
	@Column(name = "postal_code", nullable = false, unique = false)
	private String postalCode;

	@ManyToMany(
		mappedBy = "addresses", // instance variable in User class
		cascade = {
	        CascadeType.PERSIST,
	        CascadeType.MERGE
	    }
	)
    private Set<User> users = new HashSet<>();
	
	
	// Constructors
	
	public Address() {
	}

	public Address(AddressType addressType, String addressLine1, String addressLine2, String city, String province,
			String country, String postalCode) {
		this.addressType = addressType;
		this.addressLine1 = addressLine1.trim().toLowerCase();
		this.addressLine2 = addressLine2.trim().toLowerCase();
		this.city = city.trim().toLowerCase();
		this.province = province.trim().toLowerCase();
		this.country = country.trim().toLowerCase();
		this.postalCode = postalCode.trim().toLowerCase();
	}

	
	// Getters & Setters
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public AddressType getAddressType() {
		return addressType;
	}

	public void setAddressType(AddressType addressType) {
		this.addressType = addressType;
	}

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1.trim().toLowerCase();
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2.trim().toLowerCase();
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city.trim().toLowerCase();
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province.trim().toLowerCase();
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country.trim().toLowerCase();
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode.trim().toLowerCase();
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}
	
	
	// Double-Sided Setters
	
	public void addUser(User user) {
		this.users.add(user);
		user.getAddresses().add(this);
	}
	
	public void removeUser(User user) {
		this.users.remove(user);
		user.getAddresses().remove(this);
	}
	
	public void removeAllRelations() { // before removing the address do this
		new ArrayList<>(this.users).forEach(u -> this.removeUser(u));
	}

	
	// Equals & HashCode
	
	@Override
	public int hashCode() {
		return Objects.hash(addressLine1, addressLine2, addressType, city, country, postalCode, province);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Address other = (Address) obj;
		return Objects.equals(addressLine1, other.addressLine1) && Objects.equals(addressLine2, other.addressLine2)
				&& addressType == other.addressType && Objects.equals(city, other.city)
				&& Objects.equals(country, other.country) && Objects.equals(postalCode, other.postalCode)
				&& Objects.equals(province, other.province);
	}
	
}
