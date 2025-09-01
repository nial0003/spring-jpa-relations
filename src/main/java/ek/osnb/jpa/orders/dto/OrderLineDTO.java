package ek.osnb.jpa.orders.dto;

import java.time.LocalDate;

public record OrderLineDTO(Long id, double unitPrice, int quantity, String product) {
}
