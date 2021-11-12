package br.com.alura.microservice.shipping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.microservice.shipping.dto.DeliveryDTO;
import br.com.alura.microservice.shipping.dto.VoucherDTO;
import br.com.alura.microservice.shipping.model.Delivery;
import br.com.alura.microservice.shipping.service.DeliveryService;

@RestController
@RequestMapping("/delivery")
public class DeliveryController {
	
	@Autowired
	private DeliveryService deliveryServive;

	@RequestMapping(method = RequestMethod.POST)
	public VoucherDTO bookDelivery(@RequestBody DeliveryDTO orderDto) {
		return deliveryServive.bookDelivery(orderDto);
	}
}
