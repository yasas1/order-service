package com.yasas.orderservice.Repository;

import com.yasas.orderservice.Entity.OrderEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface OrderRepository extends ReactiveCrudRepository<OrderEntity, Long> {
}
