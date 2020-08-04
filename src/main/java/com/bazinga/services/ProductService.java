package com.bazinga.services;

import java.util.List;
import java.util.Optional;

import com.bazinga.modal.Product;

public interface ProductService {

	public List<Product> getProducts();
	public Optional<Product> getProduct(Long id);
	public boolean isProductAvailable(Long id);
	public boolean updateQuantity(Long id, boolean addAndRemoveQuantity);
}
