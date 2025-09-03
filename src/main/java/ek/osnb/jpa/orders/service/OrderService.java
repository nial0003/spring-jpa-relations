package ek.osnb.jpa.orders.service;


import ek.osnb.jpa.orders.dto.OrderDTO;
import ek.osnb.jpa.orders.dto.OrderLineDTO;
import ek.osnb.jpa.orders.dto.OrderUpdateDTO;
import ek.osnb.jpa.orders.model.Order;
import ek.osnb.jpa.orders.model.OrderStatus;

import java.util.List;

public interface OrderService {
    List<OrderDTO> getAllOrders(OrderStatus status);
    OrderDTO getOrderById(Long id);
    OrderDTO createOrder(OrderDTO orderDto);
    OrderDTO updateOrder(Long id, OrderDTO orderDto);
    void deleteOrder(Long id);
    OrderDTO addOrderLine(Long orderId, OrderLineDTO orderLineDTO);
    OrderDTO removeOrderLine(Long orderId, Long orderLoneId);
    OrderDTO updateOrderStatus(Long id, OrderUpdateDTO orderUpdateDTO);
}
