package br.com.alura.microservices.shop.dto;

import lombok.Data;

@Data
public class BuyItemDTO {

	private Long id;
	
	private Integer amount;
}
