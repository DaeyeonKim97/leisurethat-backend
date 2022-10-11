package com.steady.leisurethatapi.database.repository;

import com.steady.leisurethatapi.database.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
    public List<Payment> findByOrderProjectId(int projectId);
}
