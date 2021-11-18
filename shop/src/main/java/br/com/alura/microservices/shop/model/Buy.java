package br.com.alura.microservices.shop.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.ToString;

@Entity
@Data
@ToString
public class Buy {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String road;
	private String number;
	private String state;
	@Enumerated(EnumType.STRING)
	private EnumBuyStatus status;
	private String message;
	private Long orderId;
	private Integer preparationTime;
	private Long voucher;
	private LocalDate estimatedDeliveryDate;

	
}
