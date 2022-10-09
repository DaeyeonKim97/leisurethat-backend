package com.steady.leisurethatapi.database.repository;

import com.steady.leisurethatapi.database.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Integer> {
    public Project findById(int id);
    public List<Project> findByBusinessInfoMemberUsername(String username);
}
