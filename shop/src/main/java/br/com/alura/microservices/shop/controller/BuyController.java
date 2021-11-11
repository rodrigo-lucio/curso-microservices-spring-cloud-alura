package br.com.alura.microservices.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.microservices.shop.dto.BuyDTO;
import br.com.alura.microservices.shop.model.Buy;
import br.com.alura.microservices.shop.service.BuyService;

@RestController
@RequestMapping("/buy")
public class BuyController {
	
	@Autowired
	private BuyService buyService;
	
	@RequestMapping("/{id}")
	public Buy getById(@PathVariable Long id) {
		return buyService.getById(id);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public Buy makeBuy(@RequestBody BuyDTO buy) {
		return buyService.makeBuy(buy);
	}

}
