package com.bazinga.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bazinga.modal.Product;
import com.bazinga.services.ProductService;

@RestController
public class ProductController {

	@Autowired
	ProductService productservice;

	@GetMapping("/products")
	public ResponseEntity<List<Product>> getProduct() {
		return new ResponseEntity<List<Product>>(productservice.getProducts(), HttpStatus.OK);
	}

}
