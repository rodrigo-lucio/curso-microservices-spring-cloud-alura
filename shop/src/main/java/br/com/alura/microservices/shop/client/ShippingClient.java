package br.com.alura.microservices.shop.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.alura.microservices.shop.dto.DeliveryInfoDTO;
import br.com.alura.microservices.shop.dto.VoucherDTO;

@Component
@FeignClient(name = "shipping", path = "/delivery")
public interface ShippingClient {

	@PostMapping
	VoucherDTO bookDelivery(@RequestBody DeliveryInfoDTO delivery);

}
