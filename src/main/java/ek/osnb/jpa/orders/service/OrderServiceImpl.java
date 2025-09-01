package ek.osnb.jpa.orders.service;

import ek.osnb.jpa.orders.dto.OrderDTO;
import ek.osnb.jpa.orders.dto.OrderMapper;
import ek.osnb.jpa.orders.model.Order;
import ek.osnb.jpa.orders.model.OrderStatus;
import ek.osnb.jpa.orders.repository.OrderLineRepository;
import ek.osnb.jpa.orders.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderLineRepository orderLineRepository;

    public OrderServiceImpl(OrderRepository orderRepository, OrderLineRepository orderLineRepository) {
        this.orderRepository = orderRepository;
        this.orderLineRepository = orderLineRepository;
    }

    @Override
    public List<OrderDTO> getAllOrders(OrderStatus status) {
        List<Order> orders = orderRepository.findAll();
        List <OrderDTO> orderDTOS = new ArrayList<>();
        for (Order order : orders){
            orderDTOS.add(OrderMapper.toDto(order));
        }
        return orderDTOS;
    }

    @Override
    public OrderDTO getOrderById(Long id) {
        Optional<Order> order = orderRepository.findById(id);
        if (order.isPresent()) {
            return OrderMapper.toDto(order.get());
        }
        throw new RuntimeException("Order not found with id: " + id);
    }

    @Override
    public OrderDTO createOrder(OrderDTO orderDto) {
        Order order = OrderMapper.toEntity(orderDto);
        order.setId(null);
        return OrderMapper.toDto(orderRepository.save(order));
    }

    @Override
    public OrderDTO updateOrder(Long id, OrderDTO orderDto) {
        Optional<Order> existingOrder = orderRepository.findById(id);
        if (existingOrder.isPresent()){
            Order order = OrderMapper.toEntity(orderDto);
            Order updatedOrder = existingOrder.get();
            updatedOrder.setOrderDate(order.getOrderDate());
            updatedOrder.setStatus(order.getStatus());

            updatedOrder.clearOrderLines();
            for (var line : order.getOrderLines()){
                updatedOrder.addOrderLine(line);
            }

            return OrderMapper.toDto(orderRepository.save(order));
        }
        throw new RuntimeException("Order not found with id: " + id);
    }

    @Override
    public void deleteOrder(Long id) {
        if (orderRepository.existsById(id)){
            orderRepository.deleteById(id);
        }
        throw new RuntimeException("Order not found with id: " + id);
    }
}
