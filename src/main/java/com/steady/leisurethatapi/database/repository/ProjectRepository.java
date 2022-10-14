package com.steady.leisurethatapi.database.repository;

import com.steady.leisurethatapi.database.entity.Project;
import com.steady.leisurethatapi.database.entity.ProjectCategory;
import com.steady.leisurethatapi.database.entity.ProjectStatus;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.util.List;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Integer> {
    public Project findById(int id);
    public List<Project> findByStatus(ProjectStatus projectStatus);
    public List<Project> findByProjectCategoryAndStatus(ProjectCategory projectCategory, ProjectStatus projectStatus);
    @Query(value = "SELECT P.* FROM TBL_PROJECT P WHERE to_char(P.PROJECT_END_DATE,'yy/mm/dd') = TO_DATE( :date ,'yy/mm/dd')", nativeQuery = true)
    public List<Project> findByEndDate(@Param("date") Date date);
    List<Project> findAllByAccountInfoBusinessInfoMemberId(int id);
    List<Project> findAllByAccountInfoBusinessInfoMemberUsername(String username, Pageable pageable);
    public List<Project> findByBusinessInfoMemberUsername(String username);
    public List<Project> findByStatusId(int statusId, Pageable pageable);
    public int countByStatusId(int statusId);

    List<Project> findAllByAccountInfoBusinessInfoMemberUsername(String username);
}
