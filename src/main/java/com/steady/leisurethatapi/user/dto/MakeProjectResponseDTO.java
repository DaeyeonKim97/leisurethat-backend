package com.steady.leisurethatapi.user.dto;

/**
 * <pre>
 * Class : MakeProjectResponseDTO
 * Comment: 클래스에 대한 간단 설명
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-10-09       전현정           최초 생성
 * </pre>
 *
 * @author 전현정(최초 작성자)
 * @version 1(클래스 버전)
 * @see
 */
public class MakeProjectResponseDTO {

    private int projectId;
    private String projectName;
    private String projectCategory;
    private String makerName;
    private String projectStatus;
    private long targetAmount;
    private int achievement;

    public MakeProjectResponseDTO() {}

    public MakeProjectResponseDTO(int projectId, String projectName, String projectCategory,
                                  String makerName, String projectStatus, long targetAmount, int achievement) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.projectCategory = projectCategory;
        this.makerName = makerName;
        this.projectStatus = projectStatus;
        this.targetAmount = targetAmount;
        this.achievement = achievement;
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

    public String getProjectCategory() {
        return projectCategory;
    }

    public void setProjectCategory(String projectCategory) {
        this.projectCategory = projectCategory;
    }

    public String getMakerName() {
        return makerName;
    }

    public void setMakerName(String makerName) {
        this.makerName = makerName;
    }

    public String getProjectStatus() {
        return projectStatus;
    }

    public void setProjectStatus(String projectStatus) {
        this.projectStatus = projectStatus;
    }

    public long getTargetAmount() {
        return targetAmount;
    }

    public void setTargetAmount(long targetAmount) {
        this.targetAmount = targetAmount;
    }

    public int getAchievement() {
        return achievement;
    }

    public void setAchievement(int achievement) {
        this.achievement = achievement;
    }

    @Override
    public String toString() {
        return "MakeProjectResponseDTO{" +
                "projectId=" + projectId +
                ", projectName='" + projectName + '\'' +
                ", projectCategory='" + projectCategory + '\'' +
                ", makerName='" + makerName + '\'' +
                ", projectStatus='" + projectStatus + '\'' +
                ", targetAmount=" + targetAmount +
                ", achievement=" + achievement +
                '}';
    }
}
