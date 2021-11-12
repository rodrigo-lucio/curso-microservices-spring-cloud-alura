package br.com.alura.microservice.shipping.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alura.microservice.shipping.dto.DeliveryDTO;
import br.com.alura.microservice.shipping.dto.VoucherDTO;
import br.com.alura.microservice.shipping.model.Delivery;
import br.com.alura.microservice.shipping.repository.DeliveryRepository;

@Service
public class EntregaService {
	
	@Autowired
	private DeliveryRepository repository;

	public VoucherDTO bookDelivery(DeliveryDTO orderDto) {
		
		Delivery delivery = new Delivery();
		delivery.setDeliveryDate(orderDto.getDeliveryDate());
		delivery.setEstimatedDeliveryDate(orderDto.getDeliveryDate().plusDays(1l));
		delivery.setDestinationAdress(orderDto.getDestinationAdress());
		delivery.setSenderAdress(orderDto.getSenderAdress());
		delivery.setOrderId(orderDto.getOrderId());
		
		repository.save(delivery);
		
		VoucherDTO voucher = new VoucherDTO();
		voucher.setNumber(delivery.getId());
		voucher.setEstimatedDeliveryDate(delivery.getEstimatedDeliveryDate());
		return voucher;
	}

}
