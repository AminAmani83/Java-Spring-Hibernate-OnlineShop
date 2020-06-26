package com.jac.onlineshop.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.jac.onlineshop.model.Product;
import com.jac.onlineshop.service.ProductService;

@Controller
@RequestMapping(value = "/admin")
public class ProductController {

	@Autowired
	private ProductService pService;

	// Admin Product Management

	// Products
	// http://localhost:8080/OnlineShopping/admin/products
	@RequestMapping(value = "/products", method = RequestMethod.GET)
	public ModelAndView listProducts(@RequestParam(value = "message", required = false) String message) {
		System.out.println("Products List Page Laoding...");

		// display page
		ModelAndView mv = new ModelAndView("manage-products");
		mv.addObject("myAddedProduct", new Product()); // Bcs we have the form
		mv.addObject("myEditedProduct", new Product()); // Bcs we have the form
		try {
			mv.addObject("productList", pService.getAllProducts());
			mv.addObject("message", message);
		} catch (Exception exp) {
			System.out.println(exp.toString());
			mv.addObject("message", "Error fetching products.");
		}
		return mv;
	}

	// Add Product
	// http://localhost:8080/OnlineShopping/admin/addproduct
	@RequestMapping(value = "/addproduct", method = RequestMethod.POST)
	public ModelAndView addProduct(@Valid @ModelAttribute("myAddedProduct") Product product,
			BindingResult validationResult) {
		System.out.println("Adding Product... Id: " + product.getId());
		ModelAndView mv = new ModelAndView("redirect:/admin/products");

		if (validationResult.hasErrors()) { // example: string entered instead of int
			System.out.println(validationResult.getAllErrors().toString());
			mv.addObject("message", "Input Not Valid: Product not added");
			return mv;
		}

		// send the add product commands
		boolean result;
		try {
			result = pService.addProduct(product);
		} catch (Exception exp) {
			System.out.println(exp.toString());
			result = false;
		}

		// display result
		// Attributes will be sent as query parameters in the URL:
		if (result) {
			mv.addObject("message", "Product successfully added");
		} else {
			mv.addObject("message", "Error adding product");
		}
		return mv;
	}

	// Edit Product
	// http://localhost:8080/OnlineShopping/admin/editproduct
	@RequestMapping(value = "/editproduct", method = RequestMethod.POST)
	public ModelAndView editProduct(@Valid @ModelAttribute("myEditedProduct") Product product,
			BindingResult validationResult) {
		System.out.println("Editing Product... Id: " + product.getId());
		ModelAndView mv = new ModelAndView("redirect:/admin/products");

		if (validationResult.hasErrors()) { // example: string entered instead of int
			System.out.println(validationResult.getAllErrors().toString());
			mv.addObject("message", "Input Not Valid: Product not edited");
			return mv;
		}

		// send the edit product command
		boolean result;
		try {
			result = pService.updateProduct(product);
		} catch (Exception exp) {
			System.out.println(exp.toString());
			result = false;
		}

		// display result
		// Attributes will be sent as query parameters in the URL:
		if (result) {
			mv.addObject("message", "Product successfully edited");
		} else {
			mv.addObject("message", "Error editing product");
		}
		return mv;
	}

	// Delete Product
	// http://localhost:8080/OnlineShopping/admin/deleteproduct?id=1
	@RequestMapping(value = "/deleteproduct", method = RequestMethod.GET)
	public ModelAndView deleteProduct(@RequestParam("id") int productId, ModelMap model) {
		System.out.println("Removing Product... Id: " + productId);

		// send the delete product command
		boolean result;
		try {
			result = pService.deleteProduct(productId);
		} catch (Exception exp) {
			System.out.println(exp.toString());
			result = false;
		}

		// display result
		ModelAndView mv = new ModelAndView("redirect:/admin/products");
		// Attributes will be sent as query parameters in the URL:
		if (result) {
			mv.addObject("message", "Product successfully deleted");
		} else {
			mv.addObject("message", "Error deleting product");
		}
		return mv;
	}
}
