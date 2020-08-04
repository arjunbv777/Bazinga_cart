package com.bazinga.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bazinga.modal.Product;
import com.bazinga.modal.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	ProductRepository productRepo;
	
	
	public boolean isProductAvailable(Long id) {
		return getProduct(id).get().getQuantity() >= 1;
	}

	@Override
	public List<Product> getProducts() {
		return productRepo.findAll();
	}

	@Override
	public Optional<Product> getProduct(Long id) {
		return productRepo.findById(id);
	}

	@Override
	public boolean updateQuantity(Long id, boolean addAndRemoveQuantity) {
		Product product= getProduct(id).get();
		if(addAndRemoveQuantity) {			
			product.setQuantity(product.getQuantity() - 1);
			return true;
		}else {
			product.setQuantity(product.getQuantity() + 1);
			return true;
		}
	}
	
	

}
