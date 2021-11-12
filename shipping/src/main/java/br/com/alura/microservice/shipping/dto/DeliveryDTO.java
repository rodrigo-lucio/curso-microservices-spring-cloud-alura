package br.com.alura.microservice.shipping.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class DeliveryDTO {

	private Long orderId;
	private LocalDate deliveryDate;
	private String senderAdress;
	private String destinationAdress;

}
