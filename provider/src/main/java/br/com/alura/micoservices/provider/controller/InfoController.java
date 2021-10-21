package br.com.alura.micoservices.provider.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.micoservices.provider.model.ProviderInfo;
import br.com.alura.micoservices.provider.service.InfoService;

@RestController
@RequestMapping("/info")
public class InfoController {

	@Autowired
	private InfoService infoService;
	
	@GetMapping("/{state}")
	public ProviderInfo getInfoByState(@PathVariable String state) {
		return infoService.getInfoByState(state);
	}
}
