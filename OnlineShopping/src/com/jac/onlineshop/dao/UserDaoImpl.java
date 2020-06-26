package com.jac.onlineshop.dao;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jac.onlineshop.model.Address;
import com.jac.onlineshop.model.CartItem;
import com.jac.onlineshop.model.User;

@Repository
public class UserDaoImpl implements UserDao {

	// Variables
	@Autowired
	private SessionFactory sessionFactory;

	// Constructors
	public UserDaoImpl() {
	}

	// Methods
	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public List<User> getAllUsers() {
		return getSession().createQuery("from User", User.class).list();
	}

	@Override
	public boolean deleteUser(int userId) {
		User user = this.getUserById(userId);
		return this.deleteUser(user);
	}

	@Override // Overloaded
	public boolean deleteUser(User user) {
		// All cart items & Addresses related to this user must be removed too.
		// Remove any unique addresses that only belong to this user
		// (Bcs Orphan-Removal does not happen automatically for Many-to-Many)
		user.getAddresses().stream().filter(a -> a.getUsers().size() == 1)
				.forEach((a) -> this.deleteUserAddress(user.getId(), a.getId()));
		user.removeAllRelations();
		getSession().delete(user);
		return true;
	}

	@Override
	public boolean addUser(User user) {
		getSession().save(user);
		return true;
	}

	@Override
	public boolean updateUser(User user) {
		// This user Obj has everything except cartItem and Addresses sets
		User oldUser = this.getUserById(user.getId());
		user.setAddresses(oldUser.getAddresses());
		user.setCartItems(oldUser.getCartItems()); // not necessary
		getSession().update(user);
		return true;
	}

	@Override
	public User getUserById(int userId) {
		return getSession().get(User.class, userId);
	}
	

	/////////////////////////////////////////////////////////
	/////////////////// User Addresses //////////////////////
	/////////////////////////////////////////////////////////

	
	@Override
	public Address getAddressById(int addressId) {
		return getSession().get(Address.class, addressId);
	}

	@Override
	public Set<Address> getUserAddresses(int userId) {
		// Option 1. Only works if we have: @ManyToMany(fetch
		// = FetchType.EAGER) or if we use @Transactional
		// User user = this.getUserById(userId);
		// return user.getAddresses();
	
		// Option 2. Inner Join:
		String hql = "SELECT a " 			 // instead of: "SELECT a.*" in SQL
				+ "FROM Address AS a " 		 // Address: class name (not DB table)
				+ "INNER JOIN a.users AS u " // users: var in class Address
				+ "WHERE u.id = :user_id ";  // id: var in class User
		Query<Address> query = getSession().createQuery(hql, Address.class);
		query.setParameter("user_id", userId);
		return new HashSet<>(query.list());
	}

	@Override
	public boolean deleteUserAddress(int userId, int addressId) {
		Address address = this.getAddressById(addressId);
		User user = this.getUserById(userId);

		// remove address from user & user from address
		user.removeAddress(address);

		// remove address from DB altogether?
		if (address.getUsers().isEmpty()) { 
			// address belongs to no one else
			getSession().delete(address);
		}
		getSession().save(user);
		return true;
	}

	@Override
	public boolean addUserAddress(int userId, Address address) {
		User user = this.getUserById(userId);
		address.setId(0); // ID is auto-generated in DB

		// Check if this address already exists in DB
		List<Address> addressesList = findAddressByProperties(address);

		if (!addressesList.isEmpty()) { // duplicate address
			if (addressesList.get(0).getUsers().contains(user)) {
				// duplicate address for the same user
				return false; // don't add the address
			}
			// else: address shared among multiple users:
			address = addressesList.get(0); // using the same id for all
		}

		// save the address
		user.addAddress(address); // add user to address & address to user
		getSession().save(address);
		return true;
	}

	@Override
	public boolean updateUserAddress(int userId, Address address) {
		// This works whether this address belongs to this user only,
		// or if it is shared among multiple users:
		Address oldAddress = this.getAddressById(address.getId());

		// remove old address from user
		this.deleteUserAddress(userId, oldAddress.getId());

		// add new address to user
		this.addUserAddress(userId, address);
		return true;
	}

	private List<Address> findAddressByProperties(Address address) {
		// find the address object in DB ignoring its ID
		String hql = "FROM Address AS a " + "WHERE a.addressType = :address_type AND " // addressType is the instance
																						// variable in class User
				+ "a.addressLine1 = :address_line_1 AND " + "a.addressLine2 = :address_line_2 AND "
				+ "a.city = :city AND " + "a.province = :province AND " + "a.country = :country AND "
				+ "a.postalCode = :postalCode";
		Query<Address> query = getSession().createQuery(hql, Address.class);
		query.setParameter("address_type", address.getAddressType());
		query.setParameter("address_line_1", address.getAddressLine1());
		query.setParameter("address_line_2", address.getAddressLine2());
		query.setParameter("city", address.getCity());
		query.setParameter("province", address.getProvince());
		query.setParameter("country", address.getCountry());
		query.setParameter("postalCode", address.getPostalCode());
		return query.list();
	}

}
