package ek.osnb.jpa.orders.dto;

import ek.osnb.jpa.orders.model.Order;
import ek.osnb.jpa.orders.model.OrderLine;
import ek.osnb.jpa.orders.model.OrderStatus;

import java.util.ArrayList;
import java.util.List;

public class OrderMapper {
    public static OrderDTO toDto(Order order) {
        List<OrderLineDTO> orderLines = new ArrayList<>();

        for (var line : order.getOrderLines()) {
            orderLines.add(toDto(line));
        }

        return new OrderDTO(order.getId(), order.getOrderDate(), order.getStatus().name(), orderLines);
    }

    public static OrderLineDTO toDto(OrderLine orderLine){
        return new OrderLineDTO(orderLine.getId(), orderLine.getUnitPrice(), orderLine.getQuantity(), orderLine.getProduct());
    }

    public static Order toEntity (OrderDTO orderDTO){
        Order order = new Order();
        order.setOrderDate(orderDTO.orderDate());
        order.setStatus(OrderStatus.valueOf(orderDTO.status()));

        for (var lineDto : orderDTO.orderLines()){
            var line = toEntity(lineDto);
            order.addOrderLine(line);
        }

        return order;
    }

    public static OrderLine toEntity(OrderLineDTO orderLineDto) {
        OrderLine orderLine = new OrderLine();
        orderLine.setProduct(orderLineDto.product());
        orderLine.setUnitPrice(orderLineDto.unitPrice());
        orderLine.setQuantity(orderLineDto.quantity());
        return orderLine;
    }
}
