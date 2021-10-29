package br.com.alura.microservices.shop.dto;

import java.util.List;

import lombok.Data;
import lombok.ToString;

@Data 
@ToString
public class BuyDTO {
	
	private List<BuyItemDTO> items;

	private AdressDTO adress;
}
