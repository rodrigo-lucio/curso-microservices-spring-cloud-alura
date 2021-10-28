package br.com.alura.microservices.provider.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.alura.microservices.provider.dto.OrderItemDTO;
import br.com.alura.microservices.provider.model.Orders;
import br.com.alura.microservices.provider.model.OrdersItem;
import br.com.alura.microservices.provider.model.OrderStatus;
import br.com.alura.microservices.provider.model.Product;
import br.com.alura.microservices.provider.repository.OrderRepository;
import br.com.alura.microservices.provider.repository.ProductRepository;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private ProductRepository productRepository;

	public Orders getOrderById(Long id) {
		return this.orderRepository.findById(id).orElse(new Orders());
	}
	
	@Transactional
	public Orders makeOrder(List<OrderItemDTO> itens) {
		
		if(itens == null) {
			return null;
		}
		
		List<OrdersItem> orderItems = toOrderItem(itens);
		Orders order = new Orders();
		order.setItems(orderItems);
		order.setStatus(OrderStatus.RECEIVED);
		order.setPreparationTime(itens.size());
		return orderRepository.save(order);
	}

	private List<OrdersItem> toOrderItem(List<OrderItemDTO> itens) {
		
		List<Long> productsIds = itens
				.stream()
				.map(item -> item.getId())
				.collect(Collectors.toList());
		
		List<Product> orderProducts = productRepository.findByIdIn(productsIds);
		
		List<OrdersItem> orderItems = itens
			.stream()
			.map(item -> {
				Product product = orderProducts
						.stream()
						.filter(p -> p.getId() == item.getId())
						.findFirst().get();
				
				OrdersItem orderItem = new OrdersItem();
				orderItem.setProduct(product);
				orderItem.setAmount(item.getAmount());
				return orderItem;
			})
			.collect(Collectors.toList());
		return orderItems;
	}
}
