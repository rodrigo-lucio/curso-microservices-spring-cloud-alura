package br.com.alura.microservices.shop.controller.dto;

import java.util.List;

import lombok.Data;

@Data 
public class BuyDTO {
	
	private List<BuyItemDTO> items;

	private AdressDTO adress;
}
