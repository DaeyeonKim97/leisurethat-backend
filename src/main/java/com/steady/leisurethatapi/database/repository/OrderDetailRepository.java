package com.steady.leisurethatapi.database.repository;

import com.steady.leisurethatapi.database.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {


}
