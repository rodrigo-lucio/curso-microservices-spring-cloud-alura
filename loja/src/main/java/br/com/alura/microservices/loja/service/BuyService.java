package br.com.alura.microservices.loja.service;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import br.com.alura.microservices.loja.controller.dto.BuyDTO;
import br.com.alura.microservices.loja.dto.ProviderInfoDTO;

public class BuyService {

	public void makeBuy(BuyDTO buy) {
		RestTemplate client = new RestTemplate();
		ResponseEntity<ProviderInfoDTO> exchange = client.exchange("http://localhost:8081/info/" + buy.getAdress().getState(),
				HttpMethod.GET, null, ProviderInfoDTO.class);
		System.out.println(exchange);
	}

}
