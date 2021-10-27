package br.com.alura.microservices.provider.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alura.microservices.provider.model.Product;
import br.com.alura.microservices.provider.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository produtoRepository;
	
	public List<Product> getProductByState(String estado) {
		return produtoRepository.findByState(estado);
	}

}
