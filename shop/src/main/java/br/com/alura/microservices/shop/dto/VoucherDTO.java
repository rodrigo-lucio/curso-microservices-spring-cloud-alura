package br.com.alura.microservices.shop.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class VoucherDTO {

	private Long number;
	private LocalDate estimatedDeliveryDate;

}
