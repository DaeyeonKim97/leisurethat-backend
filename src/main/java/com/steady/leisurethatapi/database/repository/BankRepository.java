package com.steady.leisurethatapi.database.repository;

import com.steady.leisurethatapi.database.entity.Bank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BankRepository extends JpaRepository<Bank,Integer> {
    public Bank findById(int id);
    public List<Bank> findAllBy();
}
