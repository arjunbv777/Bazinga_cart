package com.bazinga.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bazinga.modal.CartDetails;
import com.bazinga.modal.Orders;
import com.bazinga.modal.OrdersRepository;
import com.bazinga.modal.Product;
import com.bazinga.modal.User;
import com.bazinga.modal.UserRepository;

@Service
public class OrderServiceImpl implements OrderService {

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
	public CartDetails takeOrders(Product product, User user) {
		Orders orders;
		if (productService.isProductAvailable(product.getId())) {
			orders = new Orders();
			orders.setUser(user);
			orders.setProduct(product);
			orders.setUpdatedAt(LocalDateTime.now());
			orders.setOrderQuantity(1);
			productService.updateQuantity(product.getId(), true, (long) 1);
			orders = orderrepo.save(orders);
			System.out.println(orders.toString());
		} else {
			throw new RuntimeException("Product not available");
		}
		return new CartDetails(orders, productService.getProducts());
	}

	@Override
	@Transactional
	public boolean removeOrder(Long id) {
		Orders order = orderrepo.getOne(id);
		productService.updateQuantity(order.getProduct().getId(), false, (long) order.getOrderQuantity());
		orderrepo.delete(order);
		return true;
	}

	@Override
	@Transactional
	public List<Product> updateOrder(Long id, int quantity, boolean increaseAndDecrease) {

		if (increaseAndDecrease) {
			Orders order = orderrepo.getOne(id);
			productService.updateQuantity(order.getProduct().getId(), true, (long) 1);
			order.setOrderQuantity(order.getOrderQuantity() + quantity);
			order = orderrepo.save(order);
			return productService.getProducts();
		} else {
			Orders order = orderrepo.getOne(id);
			productService.updateQuantity(order.getProduct().getId(), true, (long) -1);
			order.setOrderQuantity(order.getOrderQuantity() + quantity);
			order = orderrepo.save(order);
				if(order.getOrderQuantity() <= 0) {
					removeOrder(order.getId());
				}
			return productService.getProducts();
		}
	}

	@Override
	public Set<Orders> getOrderedProductByUSer(String user) {
		return this.getOrderedProductByUSer(userrepo.findByUsername(user).get());
	}

	@Override
	public Orders takeOrder(Product product, User user) {
		if (productService.isProductAvailable(product.getId())) {
			Orders orders = new Orders();
			orders.setUser(user);
			orders.setProduct(product);
			orders.setUpdatedAt(LocalDateTime.now());
			orders.setOrderQuantity(1);
			productService.updateQuantity(product.getId(), true, (long) 1);
			orders = orderrepo.save(orders);
			System.out.println(orders.toString());
			return orders;
		} else {
			throw new RuntimeException("Product not available");
		}
	}

}
