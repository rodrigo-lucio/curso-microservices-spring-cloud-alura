package br.com.alura.microservices.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alura.microservices.shop.model.Buy;

public interface BuyRepository extends JpaRepository<Buy, Long> {

}
