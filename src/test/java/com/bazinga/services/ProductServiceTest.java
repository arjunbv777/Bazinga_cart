package com.bazinga.services;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.bazinga.modal.ProductRepository;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles
@RunWith(SpringRunner.class)
class ProductServiceTest {

	@Autowired
	ProductRepository productRepo;
	
	@Autowired
	ProductService productService;

	
	@Test
	void testGetProducts() {
		assertEquals(9, productRepo.findAll().size(), "Total 9 products available"); 
		productRepo.findAll().forEach(e -> { System.out.println(e.toString());});
	}

	@Test
	void testGetProduct() {
		assertEquals(productRepo.findById((long) 1).get().getQuantity(), 10,"Total quantity of the produc is 10");
	}

	@Test
	void testService() {
		assertTrue(productService.isProductAvailable((long) 1));
		assertFalse(productService.isProductAvailable((long) 7));
	}
}
