package com.ecommerce.customer.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ecommerce.customer.dto.CategoryDto;
import com.ecommerce.customer.model.Category;
import com.ecommerce.customer.model.Customer;
import com.ecommerce.customer.model.Product;
import com.ecommerce.customer.model.ShoppingCart;
import com.ecommerce.customer.service.CategoryService;
import com.ecommerce.customer.service.CustomerService;
import com.ecommerce.customer.service.ProductService;

@Controller
public class ProductController {

	@Autowired
	private ProductService productService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private CustomerService customerService;

	@GetMapping("/products")
	public String products(Model model, Principal principal, HttpSession session) {
		if (principal != null) {
			session.setAttribute("username", principal.getName());
			Customer customer = customerService.findByUsername(principal.getName());
			if (customer.getShoppingCart() != null) {
				ShoppingCart cart = customer.getShoppingCart();
				session.setAttribute("totalItems", cart.getTotalItems());
			}

		} else {
			session.removeAttribute("username");
		}
		List<CategoryDto> categoryDtoList = categoryService.getCategoryAndProduct();
		List<Product> products = productService.getAllProducts();
		List<Product> listViewProducts = productService.listViewProducts();
		model.addAttribute("categories", categoryDtoList);
		model.addAttribute("viewProducts", listViewProducts);
		model.addAttribute("products", products);
		return "shop";
	}

	@GetMapping("/find-product/{id}")
	public String findProductById(@PathVariable("id") Long id, Model model) {
		Product product = productService.getProductById(id);
		Long categoryId = product.getCategory().getId();
		List<Product> products = productService.getRelatedProducts(categoryId);
		model.addAttribute("product", product);
		model.addAttribute("products", products);
		return "product-detail";
	}

	@GetMapping("/products-in-category/{id}")
	public String getProductsInCategory(@PathVariable("id") Long categoryId, Model model, Principal principal,
			HttpSession session) {
		if (principal != null) {
			session.setAttribute("username", principal.getName());
			Customer customer = customerService.findByUsername(principal.getName());
			if (customer.getShoppingCart() != null) {
				ShoppingCart cart = customer.getShoppingCart();
				session.setAttribute("totalItems", cart.getTotalItems());
			}

		} else {
			session.removeAttribute("username");
		}
		Category category = categoryService.findById(categoryId);
		List<CategoryDto> categories = categoryService.getCategoryAndProduct();
		List<Product> products = productService.getProductsInCategory(categoryId);
		model.addAttribute("category", category);
		model.addAttribute("categories", categories);
		model.addAttribute("products", products);
		return "products-in-category";
	}

	@GetMapping("/high-price")
	public String filterHighPrice(Model model, Principal principal, HttpSession session) {
		if (principal != null) {
			session.setAttribute("username", principal.getName());
			Customer customer = customerService.findByUsername(principal.getName());
			if (customer.getShoppingCart() != null) {
				ShoppingCart cart = customer.getShoppingCart();
				session.setAttribute("totalItems", cart.getTotalItems());
			}

		} else {
			session.removeAttribute("username");
		}
		List<Category> categories = categoryService.findAllByActivated();
		List<CategoryDto> categoryDtoList = categoryService.getCategoryAndProduct();
		List<Product> products = productService.filterHighPrice();
		model.addAttribute("categoryDtoList", categoryDtoList);
		model.addAttribute("products", products);
		model.addAttribute("categories", categories);
		return "filter-high-price";
	}

	@GetMapping("/low-price")
	public String filterLowPrice(Model model, Principal principal, HttpSession session) {
		if (principal != null) {
			session.setAttribute("username", principal.getName());
			Customer customer = customerService.findByUsername(principal.getName());
			if (customer.getShoppingCart() != null) {
				ShoppingCart cart = customer.getShoppingCart();
				session.setAttribute("totalItems", cart.getTotalItems());
			}

		} else {
			session.removeAttribute("username");
		}
		List<Category> categories = categoryService.findAllByActivated();
		List<CategoryDto> categoryDtoList = categoryService.getCategoryAndProduct();
		List<Product> products = productService.filterLowPrice();
		model.addAttribute("categoryDtoList", categoryDtoList);
		model.addAttribute("products", products);
		model.addAttribute("categories", categories);
		return "filter-low-price";
	}

}
