package com.steady.leisurethatapi.database.repository;

import com.steady.leisurethatapi.database.entity.Story;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StoryRepository extends JpaRepository<Story, Integer> {
    public List<Story> findByProjectId(int projectId);
}
