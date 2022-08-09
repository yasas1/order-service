package com.yasas.orderservice.repository;

import com.yasas.orderservice.entity.OrderEntity;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
public interface OrderRepository extends ReactiveCrudRepository<OrderEntity, UUID> {
    @Query("SELECT * FROM orderentity WHERE bid = :bId")
    Mono<OrderEntity> findByBId(String bId);
}
