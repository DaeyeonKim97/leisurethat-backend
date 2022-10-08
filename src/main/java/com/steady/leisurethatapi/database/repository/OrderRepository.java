package com.steady.leisurethatapi.database.repository;

import com.steady.leisurethatapi.database.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findByProjectId(int id);
}
