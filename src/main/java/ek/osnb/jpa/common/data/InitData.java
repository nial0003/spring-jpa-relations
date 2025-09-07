package ek.osnb.jpa.common.data;

import ek.osnb.jpa.orders.model.*;
import ek.osnb.jpa.orders.repository.CategoryRepository;
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
    private CategoryRepository categoryRepository;

    public InitData(OrderLineRepository orderLineRepository, OrderRepository orderRepository, CategoryRepository categoryRepository) {
        this.orderLineRepository = orderLineRepository;
        this.orderRepository = orderRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void run(String... args) {
        Category electronics = new Category();
        electronics.setName("Electronics");

        Category books = new Category();
        books.setName("Books");

        categoryRepository.saveAll(List.of(electronics, books));

        Product novel = new Product();
        novel.setName("novel");
        novel.setPrice(120);

        Product phone = new Product();
        phone.setName("Smartphone");
        phone.setPrice(1400);

        novel.getCategories().add(books);
        phone.getCategories().add(electronics);

        Order order1 = new Order(LocalDate.now(), OrderStatus.PAID);
        Order order2 = new Order(LocalDate.now(), OrderStatus.PAID);
        orderRepository.save(order1);
        orderRepository.save(order2);
        // Or use orderRepository.saveAll(List.of(order1, order2));

        OrderLine orderLine1 = new OrderLine("Product A", 50.0, 2);
        OrderLine orderLine2 = new OrderLine("Product B", 30.0, 1);

        OrderLine orderLine3 = new OrderLine("Product C", 20.0, 5);
        OrderLine orderLine4 = new OrderLine("Product D", 15.0, 3);
        OrderLine orderLine5 = new OrderLine("Product E", 25.0, 4);

        // Add the relationship
        order1.addOrderLine(orderLine1);
        order1.addOrderLine(orderLine2);
        order2.addOrderLine(orderLine3);
        order2.addOrderLine(orderLine4);
        order2.addOrderLine(orderLine5);

        orderLineRepository.saveAll(List.of(orderLine1, orderLine2, orderLine3, orderLine4, orderLine5));
    }
}
