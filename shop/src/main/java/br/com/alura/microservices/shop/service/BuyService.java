package br.com.alura.microservices.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.alura.microservices.shop.controller.dto.BuyDTO;
import br.com.alura.microservices.shop.dto.ProviderInfoDTO;

@Service
public class BuyService {

	@Autowired
	private RestTemplate client;
	
	public void makeBuy(BuyDTO buy) {
		ResponseEntity<ProviderInfoDTO> exchange = client.exchange("http://provider/info/" + buy.getAdress().getState(),
				HttpMethod.GET, null, ProviderInfoDTO.class);
		System.out.println(exchange);
	}

}
