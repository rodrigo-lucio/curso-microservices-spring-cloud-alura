package br.com.alura.microservices.loja.controller.dto;

import lombok.Data;

@Data
public class AdressDTO {

	private String road;
	private Integer number;
	private String state;
	
}
