package com.bazinga.controllers;

import java.security.Principal;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bazinga.modal.CartDetails;
import com.bazinga.modal.Orders;
import com.bazinga.modal.Product;
import com.bazinga.modal.UserRepository;
import com.bazinga.services.OrderService;

@RestController
public class OrderController {

	@Autowired
	OrderService orderService;

	@Autowired
	UserRepository userRepo;

	@GetMapping("/orders")
	public ResponseEntity<Set<Orders>> getOrders(Principal principal) {
		return ResponseEntity.ok(orderService.getOrderedProductByUSer(principal.getName()));
	}

	@PutMapping("/order")
	public ResponseEntity<Orders> createOrder(Principal principal, @RequestBody Product product) {
		System.out.println("Product is " + product.toString());
		return new ResponseEntity<Orders>(
				orderService.takeOrder(product, userRepo.findByUsername(principal.getName()).get()), HttpStatus.OK);
	}

	@GetMapping("/updateorder/{orderid}")
	public ResponseEntity<List<Product>> increaseQuantityToOrder(Principal principal,
			@PathVariable("orderid") Long orderid) {
		System.out.println("Order id " + orderid);
		return new ResponseEntity<List<Product>>(orderService.updateOrder(orderid, 1,true), HttpStatus.OK);
	}

	@GetMapping("/removeorder/{orderid}")
	public ResponseEntity<List<Product>> decreaseQuantityToOrder(Principal principal,
			@PathVariable("orderid") Long orderid) {
		System.out.println("Order id " + orderid);
		return new ResponseEntity<List<Product>>(orderService.updateOrder(orderid, -1 , false), HttpStatus.OK);
	}
	
	@DeleteMapping("/order/{orderid}")
	public ResponseEntity<Boolean> deleteOrder(Principal principal, @PathVariable("orderid") Long orderid) {
		System.out.println("Order id " + orderid);
		return new ResponseEntity(orderService.removeOrder(orderid), HttpStatus.OK);
	}
	
	@GetMapping("/cartDetails")
	public ResponseEntity<CartDetails> createOrder(Principal principal) {
		
		return new ResponseEntity<CartDetails>(new CartDetails(), HttpStatus.OK);
	} 

}
