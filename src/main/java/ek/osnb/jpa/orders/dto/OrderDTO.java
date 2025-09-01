package ek.osnb.jpa.orders.dto;

import java.time.LocalDate;
import java.util.List;

public record OrderDTO(Long id, LocalDate orderDate, String status, List<OrderLineDTO> orderLines) {
}
