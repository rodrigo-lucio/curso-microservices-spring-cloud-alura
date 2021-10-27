package br.com.alura.microservices.provider.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alura.microservices.provider.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{

}
