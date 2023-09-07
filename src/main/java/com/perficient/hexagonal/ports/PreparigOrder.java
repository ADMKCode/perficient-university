package com.perficient.hexagonal.ports;

import com.perficient.hexagonal.model.Order;

import java.util.UUID;

public interface PreparigOrder {
  Order startPreparingOrder(UUID orderId);
  Order finishPreparingOrder(UUID orderId);
}
