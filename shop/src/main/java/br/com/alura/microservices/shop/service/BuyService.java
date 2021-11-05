package br.com.alura.microservices.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alura.microservices.shop.client.ProviderClient;
import br.com.alura.microservices.shop.dto.BuyDTO;
import br.com.alura.microservices.shop.dto.OrderInfoDTO;
import br.com.alura.microservices.shop.dto.ProviderInfo;
import br.com.alura.microservices.shop.model.Buy;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BuyService {

	@Autowired
	private ProviderClient providerClient;

	public Buy makeBuy(BuyDTO buy) {
		ProviderInfo infoByState = providerClient.getInfoByState(buy.getAdress().getState());
		log.info("Get provider info by state {}", infoByState.getAdress());
		
		OrderInfoDTO order = providerClient.makeOrder(buy.getItems());
		log.info("Make order id: {}", order.getId());
		
		Buy savedBuy = new Buy();
		savedBuy.setOrderId(order.getId());
		savedBuy.setPreparationTime(order.getPreparationTime());
		savedBuy.setAdress(buy.getAdress().toString());
		log.info("Saved buy: {}", savedBuy);
		log.info("Buy DTO:  {}", buy);
		
		return savedBuy;
	}

}
