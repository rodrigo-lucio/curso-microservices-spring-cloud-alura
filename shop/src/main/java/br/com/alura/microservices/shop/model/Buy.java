package br.com.alura.microservices.shop.model;

import lombok.Data;

@Data
public class Buy {
	
	private Long id;
	private Long orderId;
	private Integer preparationTime;
	private String adress;
	
}
