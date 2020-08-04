package com.bazinga.services;

import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bazinga.modal.Orders;
import com.bazinga.modal.OrdersRepository;
import com.bazinga.modal.Product;
import com.bazinga.modal.User;
import com.bazinga.modal.UserRepository;

@Service
public class OrderServiceImpl implements OrderService{

	@Autowired
	OrdersRepository orderrepo;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	UserRepository userrepo;
	
	@Override
	public Set<Orders> getOrderedProductByUSer(User user) {
		
		return orderrepo.findAllByUser(user);
	}

	@Override
	@Transactional
	public List<Product> takeOrders(Product product, User user) {
		
		if(productService.isProductAvailable(product.getId())) {		
			Orders orders=new Orders();
			orders.setUser(user);
			orders.setProduct(product);
			
			productService.updateQuantity(product.getId(), true);
			orderrepo.save(orders);
			
		}else {
			throw new RuntimeException("Product not available");
		}
		return productService.getProducts();
	}

	@Override
	@Transactional
	public boolean removeOrder(Long id) {
		Orders order = orderrepo.getOne(id);
		productService.updateQuantity(order.getProduct().getId(), false);
		orderrepo.delete(order);	
		return true;
	}

	@Override
	@Transactional
	public List<Product> updateOrder(Long id, int quantity) {
		Orders order = orderrepo.getOne(id);
		productService.updateQuantity(order.getProduct().getId(), true);
		return productService.getProducts();
	}

	
}
