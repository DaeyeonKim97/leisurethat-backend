package com.steady.leisurethatapi.database.repository;

import com.steady.leisurethatapi.database.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    public List<Product> findByProjectId(int projectId);
}
