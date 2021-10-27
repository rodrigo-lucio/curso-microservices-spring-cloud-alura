package br.com.alura.microservices.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import br.com.alura.microservices.shop.client.ProviderClient;
import br.com.alura.microservices.shop.controller.dto.BuyDTO;
import br.com.alura.microservices.shop.dto.ProviderInfo;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BuyService {
	
	@Autowired
	private ProviderClient providerClient;
	
	public void makeBuy(BuyDTO buy) {

		ProviderInfo infoByState = providerClient.getInfoByState(buy.getAdress().getState());
		
		System.out.println(infoByState.getAdress());
	}

}
