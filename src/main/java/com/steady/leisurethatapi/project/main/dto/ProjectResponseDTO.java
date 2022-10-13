package com.steady.leisurethatapi.project.main.dto;

import com.steady.leisurethatapi.database.entity.Attachment;
import com.steady.leisurethatapi.database.entity.Project;
import com.steady.leisurethatapi.database.entity.ProjectCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectResponseDTO {
    private int projectId;
    private Attachment projectAttachment;
    private String projectName;
    private String projectCategory;
    private String memberName;
    private int targetAmount;
    private int totalAmount;
    private Date endDate;

    public ProjectResponseDTO(Project project){
        this.projectId = project.getId();
        this.projectAttachment = project.getAttachment();
        this.projectName = project.getName();
        this.projectCategory = project.getProjectCategory().getName();
        this.memberName = project.getBusinessInfo().getMember().getName();
        this.targetAmount = project.getTargetAmount();
        this.totalAmount = 0;
        this.endDate = project.getEndDate();
    }
}
