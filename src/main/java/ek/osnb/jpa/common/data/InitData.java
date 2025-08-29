package ek.osnb.jpa.common.data;

import ek.osnb.jpa.orders.model.Order;
import ek.osnb.jpa.orders.model.OrderLine;
import ek.osnb.jpa.orders.model.OrderStatus;
import ek.osnb.jpa.orders.repository.OrderLineRepository;
import ek.osnb.jpa.orders.repository.OrderRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;


@Component
public class InitData implements CommandLineRunner {

    private OrderLineRepository orderLineRepository;
    private OrderRepository orderRepository;

    public InitData(OrderLineRepository orderLineRepository, OrderRepository orderRepository) {
        this.orderLineRepository = orderLineRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public void run(String... args) {
        Order order1 = new Order(LocalDate.now(), OrderStatus.PAID);
        Order Order2 = new Order(LocalDate.now(), OrderStatus.PAID);
        orderRepository.save(order1);
        orderRepository.save(Order2);
        // Or use orderRepository.saveAll(List.of(order1, order2));

        OrderLine orderLine1 = new OrderLine("Product A", 50.0, 2);
        OrderLine orderLine2 = new OrderLine("Product B", 30.0, 1);

        OrderLine orderLine3 = new OrderLine("Product C", 20.0, 5);
        OrderLine orderLine4 = new OrderLine("Product D", 15.0, 3);
        OrderLine orderLine5 = new OrderLine("Product E", 25.0, 4);

        // Add the relationship
        orderLine1.setOrder(order1);
        orderLine2.setOrder(order1);
        orderLine3.setOrder(Order2);
        orderLine4.setOrder(Order2);
        orderLine5.setOrder(Order2);

        orderLineRepository.saveAll(List.of(orderLine1, orderLine2, orderLine3, orderLine4, orderLine5));
    }
}
