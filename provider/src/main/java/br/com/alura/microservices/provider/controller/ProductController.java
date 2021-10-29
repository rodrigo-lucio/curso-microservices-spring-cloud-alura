package br.com.alura.microservices.provider.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.microservices.provider.model.Product;
import br.com.alura.microservices.provider.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService produtoService;
	
	@GetMapping("/{state}")
	public List<Product> getProductsByState(@PathVariable("state") String estado) {
		return produtoService.getProductByState(estado);
	}
}
