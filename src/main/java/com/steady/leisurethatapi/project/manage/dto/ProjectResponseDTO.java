package com.steady.leisurethatapi.project.manage.dto;

import com.steady.leisurethatapi.database.entity.Attachment;
import com.steady.leisurethatapi.database.entity.Project;
import com.steady.leisurethatapi.database.entity.ProjectCategory;
import com.steady.leisurethatapi.database.entity.ProjectStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectResponseDTO {
    private int projectId;
    private String projectName;
    private Date projectStartDate;
    private Date projectEndDate;
    private int targetAmount;
    private String projectUrl;
    private ProjectCategory projectCategory;
    private int views;
    private ProjectStatus projectStatus;
    private Attachment projectAttachment;
    private String refundPolicy;
    private String inquiryEmail;
    private String inquiryPhone;
    public ProjectResponseDTO(Project project){
        this.projectId = project.getId();
        this.projectName = project.getName();
        this.projectStartDate = project.getStartDate();
        this.projectEndDate = project.getEndDate();
        this.targetAmount = project.getTargetAmount();
        this.projectUrl = project.getProjectURL();
        this.projectCategory = project.getProjectCategory();
        this.views = project.getViews();
        this.projectStatus = project.getStatus();
        this.projectAttachment = project.getAttachment();
        this.refundPolicy = project.getRefundPolicy();
        this.inquiryEmail = project.getInquiryEmail();
        this.inquiryPhone = project.getInquiryPhone();
    }
}
