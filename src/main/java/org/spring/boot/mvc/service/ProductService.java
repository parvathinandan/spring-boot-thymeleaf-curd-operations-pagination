package org.spring.boot.mvc.service;

import java.util.Optional;

import org.spring.boot.mvc.model.Product;
import org.spring.boot.mvc.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

	private PageRequest pageable;
	@Autowired
	private ProductRepo productRepo;
	
	public Page<Product> findAllProducts(int currentPage) {
		
		pageable = PageRequest.of(currentPage-1, 2);

		return productRepo.findAll(pageable);
	}

	public void save(Product product) {

		productRepo.save(product);
	}

	public Optional<Product> getById(Long id) {

		return productRepo.findById(id);
	}

	public void deleteById(Long id) {

		productRepo.deleteById(id);
	}
}
