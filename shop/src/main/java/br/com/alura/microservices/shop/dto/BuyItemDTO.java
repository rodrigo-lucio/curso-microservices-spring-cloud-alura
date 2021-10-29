package br.com.alura.microservices.shop.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class BuyItemDTO {

	private Long id;
	
	private Integer amount;
}
