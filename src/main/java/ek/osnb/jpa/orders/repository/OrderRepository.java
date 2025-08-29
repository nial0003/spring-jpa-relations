package ek.osnb.jpa.orders.repository;

import ek.osnb.jpa.orders.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
