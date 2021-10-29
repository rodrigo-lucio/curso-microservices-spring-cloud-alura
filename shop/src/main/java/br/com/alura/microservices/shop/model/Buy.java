package br.com.alura.microservices.shop.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Buy {
	
	private Long id;
	private Long orderId;
	private Integer preparationTime;
	private String adress;
	
}
