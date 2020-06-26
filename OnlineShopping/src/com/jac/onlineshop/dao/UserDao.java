package com.jac.onlineshop.dao;

import java.util.List;
import java.util.Set;

import com.jac.onlineshop.model.Address;
import com.jac.onlineshop.model.User;

public interface UserDao {
	List<User> getAllUsers();
	boolean deleteUser(int userId);
	boolean deleteUser(User user); // Overloaded
	boolean addUser(User user);
	boolean updateUser(User user);
	User getUserById(int userId);
	
	// Address Related
	Set<Address> getUserAddresses(int userId);
	boolean deleteUserAddress(int userId, int AddressId);
	boolean addUserAddress(int userId, Address address);
	boolean updateUserAddress(int userId, Address address);
	Address getAddressById(int AddressId);
}
