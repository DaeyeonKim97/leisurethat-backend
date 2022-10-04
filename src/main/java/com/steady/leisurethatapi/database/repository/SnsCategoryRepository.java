package com.steady.leisurethatapi.database.repository;

import com.steady.leisurethatapi.database.entity.SnsCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SnsCategoryRepository extends JpaRepository<SnsCategory, Integer> {
    public SnsCategory findById(int id);
}
