package br.com.alura.microservice.shipping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.alura.microservice.shipping.model.Delivery;

@Repository
public interface DeliveryRepository extends JpaRepository<Delivery, Long>{

}
