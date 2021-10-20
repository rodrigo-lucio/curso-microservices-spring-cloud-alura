package br.com.alura.microservices.loja.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.microservices.loja.controller.dto.BuyDTO;

@RestController
@RequestMapping("/buy")
public class BuyController {
	
	@RequestMapping(method = RequestMethod.POST)
	public void makeBuy(@RequestBody BuyDTO buy) {

	}

}
