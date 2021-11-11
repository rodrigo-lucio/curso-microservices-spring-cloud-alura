package br.com.alura.microservices.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import br.com.alura.microservices.shop.client.ProviderClient;
import br.com.alura.microservices.shop.dto.BuyDTO;
import br.com.alura.microservices.shop.dto.OrderInfoDTO;
import br.com.alura.microservices.shop.dto.ProviderInfo;
import br.com.alura.microservices.shop.model.Buy;
import br.com.alura.microservices.shop.repository.BuyRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BuyService {

	@Autowired
	private ProviderClient providerClient;
	
	@Autowired
	private BuyRepository buyService;

	@HystrixCommand(fallbackMethod = "getByIdyFallback", threadPoolKey = "getByIdThreadPool")
	public Buy getById(Long id) {	
		return buyService.findById(id).orElseThrow(() -> new EmptyResultDataAccessException(1));
	}
	
	@HystrixCommand(fallbackMethod = "makeBuyFallback", threadPoolKey = "makeBuyThreadPool")
	public Buy makeBuy(BuyDTO buy) {
		ProviderInfo infoByState = providerClient.getInfoByState(buy.getAdress().getState());
		log.info("Get provider info by state {}", infoByState.getAdress());
		
		OrderInfoDTO order = providerClient.makeOrder(buy.getItems());
		log.info("Make order id: {}", order.getId());
		
		/* Caso queira testar o fallback do Hystrix
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		*/
		
		Buy savedBuy = new Buy();
		savedBuy.setOrderId(order.getId());
		savedBuy.setPreparationTime(order.getPreparationTime());
		savedBuy.setAdress(infoByState.getAdress());
		savedBuy.setState(infoByState.getState());
		log.info("Saved buy: {}", savedBuy);
		log.info("Buy DTO:  {}", buy);
		
		return buyService.save(savedBuy);
	}
	
	public Buy makeBuyFallback(BuyDTO buy) {
		Buy savedBuy = new Buy();
		savedBuy.setAdress("Response of fallback on makeBuy");
		return savedBuy;
	}
	
	public Buy getByIdyFallback(Long id) {
		Buy savedBuy = new Buy();
		savedBuy.setAdress("Response of fallback on getById");
		return savedBuy;
	}


}
