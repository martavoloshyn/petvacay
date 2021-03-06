package com.petvacay.repositories;

import com.petvacay.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    boolean existsOrderByOrderId(long orderId);
}
