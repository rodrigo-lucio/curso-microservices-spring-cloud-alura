package br.com.alura.microservices.provider.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.alura.microservices.provider.model.ProviderInfo;

@Repository
public interface InfoRepository extends JpaRepository<ProviderInfo, Long> {

	ProviderInfo findByState(String state);
	
}
