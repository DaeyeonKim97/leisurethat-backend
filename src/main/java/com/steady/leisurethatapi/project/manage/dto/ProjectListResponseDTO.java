package com.steady.leisurethatapi.project.manage.dto;

import com.steady.leisurethatapi.database.entity.Project;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectListResponseDTO {
    private int projectId;
    private String projectName;
    private String makerName;
    private String projectStatus;
    private Date startDate;
    private Date endDate;
    private int participantNum;

    public ProjectListResponseDTO(Project project){
        this.projectId = project.getId();
        this.projectName = project.getName();
        this.projectStatus = project.getStatus().getDescription();
        this.startDate = project.getStartDate();
        this.endDate = project.getEndDate();
        this.participantNum = 0;
    }
}
