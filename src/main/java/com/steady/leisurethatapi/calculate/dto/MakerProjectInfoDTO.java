package com.steady.leisurethatapi.calculate.dto;

import java.sql.Date;

/**
 * <pre>
 * Class : MakerProjectInfoDTO
 * Comment: 제작자 정보에 들어가는 projectInfo
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-10-08       전현정           최초 생성
 * </pre>
 *
 * @author 전현정(최초 작성자)
 * @version 1(클래스 버전)
 * @see
 */
public class MakerProjectInfoDTO {

    private int projectId;          //프로젝트 아이디
    private String projectName;     //프로젝트 이름
    private int achievement;     //달성률
    private Date projectEndDate;    //프로젝트 종료일

    public MakerProjectInfoDTO() {}

    public MakerProjectInfoDTO(int projectId, String projectName, int achievement, Date projectEndDate) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.achievement = achievement;
        this.projectEndDate = projectEndDate;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public int getAchievement() {
        return achievement;
    }

    public void setAchievement(int achievement) {
        this.achievement = achievement;
    }

    public Date getProjectEndDate() {
        return projectEndDate;
    }

    public void setProjectEndDate(Date projectEndDate) {
        this.projectEndDate = projectEndDate;
    }

    @Override
    public String toString() {
        return "MakerProjectInfoDTO{" +
                "projectId=" + projectId +
                ", projectName='" + projectName + '\'' +
                ", achievement=" + achievement +
                ", projectEndDate=" + projectEndDate +
                '}';
    }
}
