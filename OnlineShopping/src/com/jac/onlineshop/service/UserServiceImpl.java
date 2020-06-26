package com.jac.onlineshop.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jac.onlineshop.dao.UserDao;
import com.jac.onlineshop.model.Address;
import com.jac.onlineshop.model.User;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	// Variables
	@Autowired
	private UserDao userDao;
	
	
	// Constructors
	public UserServiceImpl() {
	}


	// Methods
	@Override
	public List<User> getAllUsers() {
		return userDao.getAllUsers();
	}


	@Override
	public boolean deleteUser(int userId) {
		return userDao.deleteUser(userId);
	}
	
	@Override // Overloaded
	public boolean deleteUser(User user) {
		return userDao.deleteUser(user);
	}


	@Override
	public boolean addUser(User user) {
		return userDao.addUser(user);
	}


	@Override
	public boolean updateUser(User user) {
		return userDao.updateUser(user);
	}


	@Override
	public User getUserById(int userId) {
		return userDao.getUserById(userId);
	}

	// Address Related

	@Override
	public Set<Address> getUserAddresses(int userId) {
		return userDao.getUserAddresses(userId);
	}


	@Override
	public boolean deleteUserAddress(int userId, int AddressId) {
		return userDao.deleteUserAddress(userId, AddressId);
	}
	

	@Override
	public boolean addUserAddress(int userId, Address address) {
		return userDao.addUserAddress(userId, address);
	}


	@Override
	public boolean updateUserAddress(int userId, Address address) {
		return userDao.updateUserAddress(userId, address);
	}


	@Override
	public Address getAddressById(int AddressId) {
		return userDao.getAddressById(AddressId);
	}

}
