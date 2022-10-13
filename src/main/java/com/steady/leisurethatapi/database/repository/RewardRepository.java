package com.steady.leisurethatapi.database.repository;

import com.steady.leisurethatapi.database.entity.Project;
import com.steady.leisurethatapi.database.entity.Reward;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RewardRepository extends JpaRepository<Reward, Integer> {
    public Reward findById(int id);
}
