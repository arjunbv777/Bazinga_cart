package com.bazinga.services;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.bazinga.modal.CartDetails;
import com.bazinga.modal.Orders;
import com.bazinga.modal.Product;
import com.bazinga.modal.ProductRepository;
import com.bazinga.modal.UserRepository;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles
@RunWith(SpringRunner.class)
@TestMethodOrder(OrderAnnotation.class)
class OrderServiceTest {

	@Autowired
	OrderService orderService;

	@Autowired
	ProductRepository prorepo;

	@Autowired
	UserRepository userRepo;

	@Test
	@Order(1)
	void testTakeOrders() {
		CartDetails orderedProduct = orderService.takeOrders(prorepo.getOne((long) 1),
				userRepo.findByUsername("user1").get());
		assertEquals(orderedProduct.getOrders().size(), 1);
	}

	@Test
	@Order(2)
	void testGetOrderedProductByUSer() {
		Set<Orders> orders = orderService.getOrderedProductByUSer(userRepo.findByUsername("user1").get());
		// System.out.println(orders.toString());
		orders.forEach(e -> {
			System.out.println(e.toString());
		});
		assertEquals(1, orders.size());
	}

	@Test
	@Order(3)
	void testUpdateOrder() {
		Set<Orders> orders = orderService.getOrderedProductByUSer(userRepo.findByUsername("user1").get());
		System.out.println(userRepo.findByUsername("user1").get().toString());
		Iterator<Orders> iter = orders.iterator();
		List<Product> produts = orderService.updateOrder(iter.next().getId(), 1,true);
		System.out.println(produts);
		assertEquals(1, produts.size());
	}

	@Test
	@Order(4)
	void testRemoveOrder() {
		Set<Orders> orders = orderService.getOrderedProductByUSer(userRepo.findByUsername("user1").get());
		System.out.println(userRepo.findByUsername("user1").get().toString());
		Iterator<Orders> iter = orders.iterator();
		boolean produts = orderService.removeOrder(iter.next().getId());
		System.out.println(produts);
		assertEquals(0, orderService.getOrderedProductByUSer(userRepo.findByUsername("user1").get()).size());
	}

	@Test
	void testTakeOrdersWhichQuantityNotAvail() {
		orderService.takeOrders(prorepo.getOne((long) 7), userRepo.findByUsername("user1").get());

	}
}
