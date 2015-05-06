package com.voya.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import com.voya.model.Product;


@Controller
public class ProductController {
	@RequestMapping("/products")
	public ModelAndView showProducts(HttpServletRequest request) {

		List<Product> productList = new ArrayList<Product>();
		Product newProduct = new Product();
		newProduct.setName("Apple iPhone 6");
		newProduct.setColor("White");
		productList.add(newProduct);
		newProduct = new Product();
		newProduct.setName("Apple iPad 3");
		newProduct.setColor("Black");
		productList.add(newProduct);
		newProduct = new Product();
		newProduct.setName("Samsung Galaxy");
		newProduct.setColor("Silver");
		productList.add(newProduct);	
		
		ModelAndView mav = new ModelAndView("product");
		mav.addObject(productList);
		return mav;

	}
}
