package br.com.alura.microservices.shop.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.alura.microservices.shop.dto.ProviderInfo;

@Component
@FeignClient(name= "provider", path = "/info")
public interface ProviderClient {

	@GetMapping("/{state}")
	ProviderInfo getInfoByState(@PathVariable String state);
	
}
