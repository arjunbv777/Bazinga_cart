package com.bazinga.modal;

import java.util.ArrayList;
import java.util.List;

public class CartDetails {

	private Product product;
	private Orders order;
	
	List<Product> products = new ArrayList<>();
	List<Orders> orders = new ArrayList<>();
	
	public CartDetails() {}
	
	public CartDetails(Orders order,List<Product> products) {
		this.order = order;
		this.products = products;
	}
	
	public CartDetails(List<Orders> orders,List<Product> products) {
		this.orders = orders;
		this.products = products;
	}
	
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Orders getOrder() {
		return order;
	}
	public void setOrder(Orders order) {
		this.order = order;
	}
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	public List<Orders> getOrders() {
		return orders;
	}
	public void setOrders(List<Orders> orders) {
		this.orders = orders;
	}
		
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((order == null) ? 0 : order.hashCode());
		result = prime * result + ((orders == null) ? 0 : orders.hashCode());
		result = prime * result + ((product == null) ? 0 : product.hashCode());
		result = prime * result + ((products == null) ? 0 : products.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CartDetails other = (CartDetails) obj;
		if (order == null) {
			if (other.order != null)
				return false;
		} else if (!order.equals(other.order))
			return false;
		if (orders == null) {
			if (other.orders != null)
				return false;
		} else if (!orders.equals(other.orders))
			return false;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		if (products == null) {
			if (other.products != null)
				return false;
		} else if (!products.equals(other.products))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "CartDetails [product=" + product + ", order=" + order + ", products=" + products + ", orders=" + orders
				+ "]";
	}
	
	
}
