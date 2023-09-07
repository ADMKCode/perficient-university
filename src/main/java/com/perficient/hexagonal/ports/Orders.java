package com.perficient.hexagonal.ports;

import com.perficient.hexagonal.model.Order;

import java.util.UUID;

public interface Orders {
  Order findOrderById(UUID orderId);
  Order save(Order order);
  void deleteById(UUID orderId);
}
