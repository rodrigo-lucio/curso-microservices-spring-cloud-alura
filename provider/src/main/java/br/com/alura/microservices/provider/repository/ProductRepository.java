package br.com.alura.microservices.provider.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alura.microservices.provider.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

	List<Product> findByState(String estado);
	
	List<Product> findByIdIn(List<Long> ids);
}
