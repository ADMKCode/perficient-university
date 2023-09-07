package com.perficient.hexagonal.ports;

import java.util.UUID;

public interface Payment {
  Payment findPaymentByOrderId(UUID orderId);
  Payment save(Payment payment);
}
