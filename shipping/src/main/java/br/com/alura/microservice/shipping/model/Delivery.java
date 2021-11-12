package br.com.alura.microservice.shipping.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Delivery {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long orderId;
	private LocalDate deliveryDate;
	private LocalDate estimatedDeliveryDate;
	private String senderAdress;
	private String destinationAdress;
	
}
