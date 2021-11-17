package br.com.alura.microservices.shop.dto;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DeliveryInfoDTO {

	private Long orderId;
	private LocalDate deliveryDate;
	private String senderAdress;
	private String destinationAdress;
}
