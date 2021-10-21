package br.com.alura.microservices.provider.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alura.microservices.provider.model.ProviderInfo;
import br.com.alura.microservices.provider.repository.InfoRepository;

@Service
public class InfoService {

	@Autowired
	private InfoRepository infoRepository;
	
	public ProviderInfo getInfoByState(String state) {
		return infoRepository.findByState(state);
	}

}
