package com.ecom.micro.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.ecom.micro.enums.OrderStatus;

import lombok.Data;

@Data
public class OrderResponseDto {
    private Long id;
    private BigDecimal totalAmount;
    private OrderStatus status;
    private List<OrderItemDto> items;
    private LocalDateTime createdAt;
}
