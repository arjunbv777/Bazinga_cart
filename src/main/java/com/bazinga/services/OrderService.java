package com.bazinga.services;

import java.util.List;
import java.util.Set;

import com.bazinga.modal.Orders;
import com.bazinga.modal.Product;
import com.bazinga.modal.User;

public interface OrderService {

	public Set<Orders> getOrderedProductByUSer(User user);
	public List<Product> takeOrders(Product product, User user);
	public boolean removeOrder(Long id);
	public List<Product> updateOrder(Long id,int quantity);
	public Set<Orders> getOrderedProductByUSer(String user);
}
