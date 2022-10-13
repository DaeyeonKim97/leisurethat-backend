package com.steady.leisurethatapi.database.repository;

import com.steady.leisurethatapi.database.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findByProjectId(int id);
    Order findById(int id);
    int countByProjectId(int id);
}
