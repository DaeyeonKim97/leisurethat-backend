package com.steady.leisurethatapi.database.repository;

import com.steady.leisurethatapi.database.entity.Order;
import com.steady.leisurethatapi.database.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {

    public Payment findByOrderAndBillingKeyIsNotNull(Order order);

}
