package com.jac.onlineshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.jac.onlineshop.service.ProductService;
import com.jac.onlineshop.service.UserService;

@Controller
public class ShopController {

	@Autowired
	private UserService uService;

	@Autowired
	private ProductService pService;

	// Main Controller
	
	// Login Page
	// http://localhost:8080/OnlineShopping/
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView login() {
		System.out.println("Login Page Laoding...");

		// display page
		ModelAndView mv = new ModelAndView("login");
		mv.addObject("userList", uService.getAllUsers());
		return mv;
	}

	// Shop
	// http://localhost:8080/OnlineShopping/shop?userid=1
	@RequestMapping(value = "/shop", method = RequestMethod.GET)
	public ModelAndView showShop(@RequestParam("userid") int userId) {
		System.out.println("Shop Page Laoding...");

		// display page
		ModelAndView mv = new ModelAndView("shop");
		mv.addObject("productList", pService.getAllProducts());
		mv.addObject("userId", userId);
		return mv;
	}

	// Product Page
	// http://localhost:8080/OnlineShopping/product?productid=1&userid=1
	@RequestMapping(value = "/product", method = RequestMethod.GET)
	public ModelAndView showProduct(@RequestParam("productid") int productId, @RequestParam("userid") int userId) {
		System.out.println("Product Page Laoding...");

		// display page
		ModelAndView mv = new ModelAndView("product");
		mv.addObject("product", pService.getProductById(productId));
		mv.addObject("userId", userId);
		return mv;
	}

}
