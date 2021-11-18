package br.com.alura.microservices.shop.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.ToString;

@Data 
@ToString
public class BuyDTO {
	
	@JsonIgnore
	private Long id;
	
	private List<BuyItemDTO> items;

	private AdressDTO adress;
}
