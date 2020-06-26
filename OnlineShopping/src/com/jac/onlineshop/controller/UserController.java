package com.jac.onlineshop.controller;

import java.util.Arrays;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.jac.onlineshop.model.Address;
import com.jac.onlineshop.model.AddressType;
import com.jac.onlineshop.model.User;
import com.jac.onlineshop.model.UserGender;
import com.jac.onlineshop.service.UserService;

@Controller
@RequestMapping(value = "/admin")
public class UserController {

	@Autowired
	private UserService uService;

	// Admin User Management

	// Users
	// http://localhost:8080/OnlineShopping/admin/users
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public String listUsers(@RequestParam(value = "message", required = false) String message, Model model) {
		System.out.println("Users Page Laoding...");

		// display page
		model.addAttribute("myAddedUser", new User()); // Bcs we have the form
		model.addAttribute("myEditedUser", new User()); // Bcs we have the form
		model.addAttribute("genderList", Arrays.asList(UserGender.values())); // For select box
		try {
			model.addAttribute("userList", uService.getAllUsers());
			model.addAttribute("message", message);
		} catch (Exception exp) {
			System.out.println(exp.toString());
			model.addAttribute("message", "Error fetching user list.");
		}
		return "manage-users";
	}

// Add User
// http://localhost:8080/OnlineShopping/admin/adduser
@RequestMapping(value = "/adduser", method = RequestMethod.POST)
public String addUser(@Valid @ModelAttribute("myAddedUser") User user,
		BindingResult validationResult, Model model) {
	System.out.println("Adding User... Id: " + user.getId());

	if (validationResult.hasErrors()) {
		System.out.println(validationResult.getAllErrors().toString());
		model.addAttribute("message", "Input Not Valid: User not added");
		return "redirect:/admin/users";
	}
	
	// send the add user commands
	boolean result;
	try {
		result = uService.addUser(user);
	} catch (Exception exp) {
		System.out.println(exp.toString());
		result = false;
	}

		// display result
		// model attributes will be sent as query parameters in the URL:
		if (result) {
			model.addAttribute("message", "User successfully added");
		} else {
			model.addAttribute("message", "Error adding user");
		}
		return "redirect:/admin/users";
	}

	// Edit User
	// http://localhost:8080/OnlineShopping/admin/edituser
	@RequestMapping(value = "/edituser", method = RequestMethod.POST)
	public String editUser(@Valid @ModelAttribute("myEditedUser") User user, BindingResult validationResult, Model model) {
		System.out.println("Editing User... Id: " + user.getId());

		if (validationResult.hasErrors()) { // example: string entered instead of int
			System.out.println(validationResult.getAllErrors().toString());
			model.addAttribute("message", "Input Not Valid: User not edited");
			return "redirect:/admin/users";
		}
		
		// send the edit user command
		boolean result;
		try {
			result = uService.updateUser(user);
		} catch (Exception exp) {
			System.out.println(exp.toString());
			result = false;
		}
		
		// display result
		// model attributes will be sent as query parameters in the URL:
		if (result) {
			model.addAttribute("message", "User successfully edited");
		} else {
			model.addAttribute("message", "Error editing user");
		}
		return "redirect:/admin/users";
	}

	// Delete User
	// http://localhost:8080/OnlineShopping/admin/deleteuser?id=1
	@RequestMapping(value = "/deleteuser", method = RequestMethod.GET)
	public String deleteUser(@RequestParam("id") int userId, Model model) {
		System.out.println("Removing User... Id: " + userId);

		// send the delete user command
		boolean result;
		try {
			result = uService.deleteUser(userId);
		} catch (Exception exp) {
			System.out.println(exp.toString());
			result = false;
		}
		
		// display result
		// model attributes will be sent as query parameters in the URL:
		if (result) {
			model.addAttribute("message", "User successfully deleted");
		} else {
			model.addAttribute("message", "Error deleting user");
		}
		return "redirect:/admin/users";
	}
	

	/////////////////////////////////////////////////////////
	//                    User Addresses                   //
	/////////////////////////////////////////////////////////

	
	// User Addresses
	// http://localhost:8080/OnlineShopping/admin/useraddresses?userid=1
	@RequestMapping(value = "/useraddresses", method = RequestMethod.GET)
	public String listUserAddresses(@RequestParam("userid") int userId,
			@RequestParam(value = "message", required = false) String message, Model model) {
		System.out.println("User Addresses Page Laoding...");

		model.addAttribute("myAddedAddress", new Address()); // For the form
		model.addAttribute("myEditedAddress", new Address()); // For the form
		
		User user = uService.getUserById(userId);
		if (user == null) {
			model.addAttribute("message", "Error: User does not exist.");
			return "manage-user-addresses";
		}
		
		// display page
		model.addAttribute("userId", userId);
		model.addAttribute("addressTypeList", Arrays.asList(AddressType.values())); // For select box
		try {
			model.addAttribute("addressList", uService.getUserAddresses(userId));
			model.addAttribute("message", message);
		} catch (Exception exp) {
			System.out.println(exp.toString());
			model.addAttribute("message", "Error fetching Addresses for this user.");
		}
		return "manage-user-addresses";
	}

	// Add User Address
	// http://localhost:8080/OnlineShopping/admin/adduseraddress?userid=1
	@RequestMapping(value = "/adduseraddress", method = RequestMethod.POST)
	public String addUserAddress(@RequestParam("userid") int userId, @ModelAttribute("myAddedAddress") Address address,
			Model model) {
		System.out.println("Adding User Adress... Id: " + userId);

		// send the add user address command
		boolean result;
		try {
			result = uService.addUserAddress(userId, address);
		} catch (Exception exp) {
			System.out.println(exp.toString());
			result = false;
		}

		// display result
		// model attributes will be sent as query parameters in the URL:
		model.addAttribute("userid", userId);
		if (result) {
			model.addAttribute("message", "Address successfully added");
		} else {
			model.addAttribute("message", "Error adding address");
		}
		return "redirect:/admin/useraddresses";
	}

	// Delete User Address
	// http://localhost:8080/OnlineShopping/admin/deleteuseraddress?userid=1&addressid=1
	@RequestMapping(value = "/deleteuseraddress", method = RequestMethod.GET)
	public String deleteUserAddress(@RequestParam("userid") int userId, @RequestParam("addressid") int addressId,
			Model model) {
		System.out.println("Removing User Address... Id: " + userId);

		// send the delete user address command
		boolean result;
		try {
			result = uService.deleteUserAddress(userId, addressId);
		} catch (Exception exp) {
			System.out.println(exp.toString());
			result = false;
		}
		
		// display result
		// model attributes will be sent as query parameters in the URL:
		model.addAttribute("userid", userId);
		if (result) {
			model.addAttribute("message", "Address successfully deleted");
		} else {
			model.addAttribute("message", "Error deleting address");
		}
		return "redirect:/admin/useraddresses";
	}

	// Edit User Address
	// http://localhost:8080/OnlineShopping/admin/edituseraddress?userid=1
	@RequestMapping(value = "/edituseraddress", method = RequestMethod.POST)
	public String editUserAddress(@RequestParam("userid") int userId,
			@ModelAttribute("myEditedUserAddress") Address address, Model model) {
		System.out.println("Editing Address... Id: " + address.getId());

		// send the edit user address command
		boolean result;
		try {
			result = uService.updateUserAddress(userId, address);
		} catch (Exception exp) {
			System.out.println(exp.toString());
			result = false;
		}

		// display result
		// model attributes will be sent as query parameters in the URL:
		model.addAttribute("userid", userId);
		if (result) {
			model.addAttribute("message", "Address successfully edited");
		} else {
			model.addAttribute("message", "Error editing address");
		}
		return "redirect:/admin/useraddresses";
	}

}
