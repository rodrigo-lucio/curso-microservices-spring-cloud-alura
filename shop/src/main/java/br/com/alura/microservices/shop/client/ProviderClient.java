package br.com.alura.microservices.shop.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.alura.microservices.shop.dto.BuyItemDTO;
import br.com.alura.microservices.shop.dto.OrderInfoDTO;
import br.com.alura.microservices.shop.dto.ProviderInfo;

@Component
@FeignClient(name= "provider")
public interface ProviderClient {

	@GetMapping("/info/{state}")
	ProviderInfo getInfoByState(@PathVariable String state);
	
	@RequestMapping(method = RequestMethod.POST, value = "/order")
	OrderInfoDTO makeOrder(@RequestBody List<BuyItemDTO> products); 
	
}
