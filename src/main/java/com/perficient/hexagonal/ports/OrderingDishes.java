package com.perficient.hexagonal.ports;

import com.perficient.hexagonal.model.Order;

import java.util.UUID;

public interface OrderingDishes {
  Order placeOrder(Order order);
  Order updateOrder(UUID orderId, Order order);
  void cancelOrder(UUID orderId);
//  Payment payOrder(UUID orderId, CreditCard creditCard);
//  Receipt readReceipt(UUID orderId);
  Order takeOrder(UUID orderId);
}
