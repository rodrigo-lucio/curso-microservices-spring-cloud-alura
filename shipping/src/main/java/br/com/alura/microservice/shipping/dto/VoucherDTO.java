package br.com.alura.microservice.shipping.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class VoucherDTO {

	private Long number;
	private LocalDate estimatedDeliveryDate;

}
