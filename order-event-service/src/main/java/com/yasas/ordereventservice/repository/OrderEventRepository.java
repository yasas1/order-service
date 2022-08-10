package com.yasas.ordereventservice.repository;

import com.yasas.ordereventservice.entity.OrderEvent;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderEventRepository extends ReactiveMongoRepository<OrderEvent, String> {
}
