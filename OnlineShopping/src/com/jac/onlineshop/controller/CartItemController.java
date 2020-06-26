package com.jac.onlineshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.jac.onlineshop.model.CartItem;
import com.jac.onlineshop.model.Product;
import com.jac.onlineshop.model.User;
import com.jac.onlineshop.service.CartItemService;
import com.jac.onlineshop.service.ProductService;
import com.jac.onlineshop.service.UserService;

@Controller
public class CartItemController {

	@Autowired
	private CartItemService cService;

	@Autowired
	private UserService uService;

	@Autowired
	private ProductService pService;
	
	// User Cart Management

	// Shopping Cart
	// http://localhost:8080/OnlineShopping/cart?userid=1
	@RequestMapping(value = "/cart", method = RequestMethod.GET)
	public ModelAndView listCartItems(@RequestParam("userid") int userId,
			@RequestParam(value = "message", required = false) String message) {
		System.out.println("Cart Page Laoding... ID: " + userId);

		ModelAndView mv = new ModelAndView("cart");
		
		User user = uService.getUserById(userId);
		if (user == null) {
			mv.addObject("message", "Error: User does not exist.");
			return mv;
		}
		
		// display page
		mv.addObject("userId", userId);
		try {
			mv.addObject("cartItemList", cService.getAllCartItems(user));
			mv.addObject("message", message);
		} catch (Exception exp) {
			System.out.println(exp.toString());
			mv.addObject("message", "Error fetching cart items for this user.");
		}
		return mv;
	}

	// Add Cart Item
	// http://localhost:8080/OnlineShopping/addcartitem?productid=1&quantity=1&userid=1
	@RequestMapping(value = "/addcartitem", method = RequestMethod.GET)
	public ModelAndView addCartItem(@RequestParam("productid") int productId, @RequestParam("userid") int userId,
			@RequestParam("quantity") int quantity) {
		System.out.println("Adding Cart Item... ");

		// send the add cart item command
		boolean result;
		try {
			User user = uService.getUserById(userId);
			Product product = pService.getProductById(productId);
			CartItem cartItem = new CartItem(user, product, quantity);
			result = cService.addCartItem(cartItem);
		} catch (Exception exp) {
			System.out.println(exp.toString());
			result = false;
		}
		
		// display result
		ModelAndView mv = new ModelAndView("redirect:/cart");
		// Attributes will be sent as query parameters in the URL:
		mv.addObject("userid", userId);
		if (result) {
			mv.addObject("message", "Product successfully added to cart");
		} else {
			mv.addObject("message", "Error adding product to cart");
		}
		return mv;
	}

	// Edit Cart Item
	// http://localhost:8080/OnlineShopping/editcartitem?cartitemid=1&newquantity=1
	@RequestMapping(value = "/editcartitem", method = RequestMethod.GET)
	public ModelAndView editCartItem(@RequestParam("cartitemid") int cartItemId,
			@RequestParam("newquantity") int newquantity) {
		System.out.println("Editing Cart Item... Id: " + cartItemId);

		// send the edit cart item command
		int userId = 0;
		boolean result;
		try {
			CartItem cartItem = cService.getCartItemById(cartItemId);
			userId = cartItem.getUser().getId();
			cartItem.setQuantity(newquantity);
			result = cService.updateCartItem(cartItem);
		} catch (Exception exp) {
			System.out.println(exp.toString());
			result = false;
		}
		
		// display result
		ModelAndView mv = new ModelAndView("redirect:/cart");
		// Attributes will be sent as query parameters in the URL:
		mv.addObject("userid", userId);
		if (result) {
			mv.addObject("message", "Cart item successfully edited");
		} else {
			mv.addObject("message", "Error editing cart item");
		}
		return mv;
	}

	// Delete Cart Item
	// http://localhost:8080/OnlineShopping/deletecartitem?cartitemid=1
	@RequestMapping(value = "/deletecartitem", method = RequestMethod.GET)
	public ModelAndView deleteCartItem(@RequestParam("cartitemid") int cartItemId) {
		System.out.println("Removing Cart Item... Id: " + cartItemId);

		// send the delete cart item command
		int userId = 0;
		boolean result;
		try {
			CartItem cartItem = cService.getCartItemById(cartItemId);
			userId = cartItem.getUser().getId();
			result = cService.deleteCartItem(cartItemId);
		} catch (Exception exp) {
			System.out.println(exp.toString());
			result = false;
		}

		// display result
		ModelAndView mv = new ModelAndView("redirect:/cart");
		// Attributes will be sent as query parameters in the URL:
		mv.addObject("userid", userId);
		if (result) {
			mv.addObject("message", "Cart item successfully deleted");
		} else {
			mv.addObject("message", "Error deleting cart item");
		}
		return mv;
	}
}
