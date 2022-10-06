package com.steady.leisurethatapi.database.repository;

import com.steady.leisurethatapi.database.entity.ProjectStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectStatusRepository extends JpaRepository<ProjectStatus, Integer> {
    public ProjectStatus findById(int id);
}
