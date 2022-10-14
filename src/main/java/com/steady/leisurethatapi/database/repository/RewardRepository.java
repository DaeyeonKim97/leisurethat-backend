package com.steady.leisurethatapi.database.repository;

import com.steady.leisurethatapi.database.entity.Reward;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RewardRepository extends JpaRepository<Reward, Integer> {
    public Reward findById(int id);
    public List<Reward> findByProjectId(int projectId);
}
