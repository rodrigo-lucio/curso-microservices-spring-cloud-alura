package br.com.alura.microservices.provider.dto;

import lombok.Data;

@Data
public class OrderItemDTO {

	private long productId;	
	private int amount;
	
}
