package br.com.alura.microservices.loja.controller.dto;

import lombok.Data;

@Data
public class BuyItemDTO {

	private Long id;
	
	private Integer amount;
}
