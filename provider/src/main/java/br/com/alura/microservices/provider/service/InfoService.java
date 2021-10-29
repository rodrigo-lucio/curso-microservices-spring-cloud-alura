package br.com.alura.microservices.provider.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alura.microservices.provider.model.ProviderInfo;
import br.com.alura.microservices.provider.repository.InfoRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class InfoService {

	@Autowired
	private InfoRepository infoRepository;
	
	public ProviderInfo getInfoByState(String state) {
		log.info("Get provider info by state {}", state);
		return infoRepository.findByState(state);
	}

}
