package br.com.alura.microservices.shop.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import br.com.alura.microservices.shop.client.ProviderClient;
import br.com.alura.microservices.shop.client.ShippingClient;
import br.com.alura.microservices.shop.dto.BuyDTO;
import br.com.alura.microservices.shop.dto.DeliveryInfoDTO;
import br.com.alura.microservices.shop.dto.OrderInfoDTO;
import br.com.alura.microservices.shop.dto.ProviderInfo;
import br.com.alura.microservices.shop.dto.VoucherDTO;
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
	
	@Autowired
	private ShippingClient shippingClient;

	//threadPoolKey=caso o makeBuy fique com as 10 threads cheias (padrao do histryx, ele nao bloqueia a execuçcao do get by id
	@HystrixCommand(fallbackMethod = "getByIdyFallback", threadPoolKey = "makeBuyThreadPool") 
	public Buy getById(Long id) {	
		return buyService.findById(id).orElseThrow(() -> new EmptyResultDataAccessException(1));
	}
	
	@HystrixCommand(fallbackMethod = "makeBuyFallback", threadPoolKey = "makeBuyThreadPool")
	public Buy makeBuy(BuyDTO buy) {
		ProviderInfo infoByState = providerClient.getInfoByState(buy.getAdress().getState());
		log.info("Get provider info by state {}", infoByState.getAdress());
		
		OrderInfoDTO order = providerClient.makeOrder(buy.getItems());
		log.info("Make order id: {}", order.getId());
		
		DeliveryInfoDTO delivery = DeliveryInfoDTO.builder()
									.orderId(order.getId())
									.deliveryDate(LocalDate.now().plusDays(order.getPreparationTime()))
									.senderAdress(infoByState.getAdress())
									.destinationAdress(buy.getAdress().toString())
									.build();

		VoucherDTO voucher = shippingClient.bookDelivery(delivery);
		log.info("Book delivery voucher: {}", voucher.getNumber());
		
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
		savedBuy.setVoucher(voucher.getNumber());
		savedBuy.setEstimatedDeliveryDate(voucher.getEstimatedDeliveryDate());
		
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
