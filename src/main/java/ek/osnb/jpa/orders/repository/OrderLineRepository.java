package ek.osnb.jpa.orders.repository;

import ek.osnb.jpa.orders.model.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderLineRepository extends JpaRepository<OrderLine, Long> {
}
