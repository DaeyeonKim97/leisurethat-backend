package com.steady.leisurethatapi.database.repository;

import com.steady.leisurethatapi.database.entity.ProjectCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectCategoryRepository extends JpaRepository<ProjectCategory, Integer> {
    public ProjectCategory findById(int id);
    public List<ProjectCategory> findAllBy();
}
