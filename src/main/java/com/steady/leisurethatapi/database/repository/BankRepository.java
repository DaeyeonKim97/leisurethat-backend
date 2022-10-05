package com.steady.leisurethatapi.database.repository;

import com.steady.leisurethatapi.database.entity.Bank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankRepository extends JpaRepository<Bank,Integer> {
    public Bank findById();
}
