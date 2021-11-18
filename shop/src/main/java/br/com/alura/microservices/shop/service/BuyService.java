package br.com.alura.microservices.shop.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import br.com.alura.microservices.shop.client.ProviderClient;
import br.com.alura.microservices.shop.client.ShippingClient;
import br.com.alura.microservices.shop.dto.BuyDTO;
import br.com.alura.microservices.shop.dto.DeliveryInfoDTO;
import br.com.alura.microservices.shop.dto.OrderInfoDTO;
import br.com.alura.microservices.shop.dto.ProviderInfo;
import br.com.alura.microservices.shop.dto.VoucherDTO;
import br.com.alura.microservices.shop.model.Buy;
import br.com.alura.microservices.shop.model.EnumBuyStatus;
import br.com.alura.microservices.shop.repository.BuyRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BuyService {

	@Autowired
	private ProviderClient providerClient;
	
	@Autowired
	private BuyRepository buyRepository;
	
	@Autowired
	private ShippingClient shippingClient;

	//threadPoolKey=caso o makeBuy fique com as 10 threads cheias (padrao do histryx, ele nao bloqueia a execuçcao do get by id
	@HystrixCommand(fallbackMethod = "getByIdyFallback", threadPoolKey = "makeBuyThreadPool") 
	public Buy getById(Long id) {	
		return buyRepository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException(1));
	}
	
	@Transactional
	@HystrixCommand(fallbackMethod = "makeBuyFallback", threadPoolKey = "makeBuyThreadPool")
	public Buy makeBuy(BuyDTO buyInput) {
		Buy buy = saveBuyReceived(buyInput);
		buyInput.setId(buy.getId());
		OrderInfoDTO order = makeOrder(buyInput, buy);
		bookDelivery(buyInput, buy, order);
		/* Caso queira testar o fallback do Hystrix
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		*/
		log.info("Saved buy: {}", buy);
		log.info("Buy DTO:  {}", buyInput);
		
		return buy;
	}

	private Buy saveBuyReceived(BuyDTO buyInput) {
		Buy buy = new Buy();
		buy.setStatus(EnumBuyStatus.RECEIVED);
		buy.setRoad(buyInput.getAdress().getRoad());
		buy.setState(buyInput.getAdress().getState());
		buy.setNumber(buyInput.getAdress().getNumber());
		buyRepository.save(buy);
		return buy;
	}
	
	private OrderInfoDTO makeOrder(BuyDTO buyInput, Buy buy) {
		OrderInfoDTO order = providerClient.makeOrder(buyInput.getItems());
		log.info("Make order id: {}", order.getId());
		buy.setOrderId(order.getId());
		buy.setPreparationTime(order.getPreparationTime());
		buy.setStatus(EnumBuyStatus.ORDER_RECEIVED);
		buyRepository.save(buy);
		return order;
	}
	
	private void bookDelivery(BuyDTO buyInput, Buy buy, OrderInfoDTO order) {
		ProviderInfo infoByState = providerClient.getInfoByState(buyInput.getAdress().getState());
		log.info("Get provider info by state {}", infoByState.getAdress());
		DeliveryInfoDTO delivery = DeliveryInfoDTO.builder()
									.orderId(order.getId())
									.deliveryDate(LocalDate.now().plusDays(order.getPreparationTime()))
									.senderAdress(infoByState.getAdress())
									.destinationAdress(buyInput.getAdress().toString())
									.build();

		VoucherDTO voucher = shippingClient.bookDelivery(delivery);
		log.info("Book delivery voucher: {}", voucher.getNumber());
		buy.setVoucher(voucher.getNumber());
		buy.setEstimatedDeliveryDate(voucher.getEstimatedDeliveryDate());
		buy.setStatus(EnumBuyStatus.DELIVERY_BOOKED);
		buyRepository.save(buy);
	}
	
	public Buy makeBuyFallback(BuyDTO buy) {
		Buy savedBuy = buyRepository.findById(buy.getId())
						.orElseThrow(() -> 
							new EmptyResultDataAccessException(String.format("Compra não encontrada para o id %d", buy.getId()), 1));
		savedBuy.setMessage("Response of fallback on makeBuy");
		buyRepository.save(savedBuy);
		return savedBuy;
	}
	
	public Buy getByIdyFallback(Long id) {
		Buy savedBuy = new Buy();
		savedBuy.setMessage("Response of fallback on getById");
		return savedBuy;
	}

}
