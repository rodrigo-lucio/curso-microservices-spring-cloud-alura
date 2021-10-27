package br.com.alura.microservices.provider.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.microservices.provider.dto.OrderItemDTO;
import br.com.alura.microservices.provider.model.Order;
import br.com.alura.microservices.provider.service.OrderService;

@RestController
@RequestMapping("order")
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@RequestMapping(method = RequestMethod.POST)
	public Order makeOrder(@RequestBody List<OrderItemDTO> products) {
		return orderService.makeOrder(products);
	}
	
	@RequestMapping("/{id}")
	public Order getOrderById(@PathVariable Long id) {
		return orderService.getOrderById(id);
	}
}
