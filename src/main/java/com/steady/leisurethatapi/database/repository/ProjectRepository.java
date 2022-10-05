package com.steady.leisurethatapi.database.repository;

import com.steady.leisurethatapi.database.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Integer> {
    public Project findById(int id);
}
