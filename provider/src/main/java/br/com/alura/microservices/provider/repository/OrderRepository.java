package br.com.alura.microservices.provider.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alura.microservices.provider.model.Orders;

public interface OrderRepository extends JpaRepository<Orders, Long>{

}
