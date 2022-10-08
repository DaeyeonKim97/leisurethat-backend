package com.steady.leisurethatapi.calculate.dto;

import java.sql.Date;

/**
 * <pre>
 * Class : CalculateListReponseDTO
 * Comment: 관리자 정산 신청 현황 리스트 DTO
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-10-07       전현정           최초 생성
 * </pre>
 *
 * @author 전현정(최초 작성자)
 * @version 1(클래스 버전)
 * @see
 */
public class CalculateListReponseDTO {

    private int projectId;
    private int calculateId;
    private int makerId;
    private String makerUsername;
    private String makerName;
    private String projectName;
    private String calculateRound;
    private String calculateStatus;
    private Date calculateRegDate;

    public CalculateListReponseDTO() {}

    public CalculateListReponseDTO(int projectId, int calculateId, int makerId, String makerUsername, String makerName, String projectName, String calculateRound, String calculateStatus, Date calculateRegDate) {
        this.projectId = projectId;
        this.calculateId = calculateId;
        this.makerId = makerId;
        this.makerUsername = makerUsername;
        this.makerName = makerName;
        this.projectName = projectName;
        this.calculateRound = calculateRound;
        this.calculateStatus = calculateStatus;
        this.calculateRegDate = calculateRegDate;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getCalculateId() {
        return calculateId;
    }

    public void setCalculateId(int calculateId) {
        this.calculateId = calculateId;
    }

    public int getMakerId() {
        return makerId;
    }

    public void setMakerId(int makerId) {
        this.makerId = makerId;
    }

    public String getMakerUsername() {
        return makerUsername;
    }

    public void setMakerUsername(String makerUsername) {
        this.makerUsername = makerUsername;
    }

    public String getMakerName() {
        return makerName;
    }

    public void setMakerName(String makerName) {
        this.makerName = makerName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getCalculateRound() {
        return calculateRound;
    }

    public void setCalculateRound(String calculateRound) {
        this.calculateRound = calculateRound;
    }

    public String getCalculateStatus() {
        return calculateStatus;
    }

    public void setCalculateStatus(String calculateStatus) {
        this.calculateStatus = calculateStatus;
    }

    public Date getCalculateRegDate() {
        return calculateRegDate;
    }

    public void setCalculateRegDate(Date calculateRegDate) {
        this.calculateRegDate = calculateRegDate;
    }

    @Override
    public String toString() {
        return "CalculateListReponseDTO{" +
                "projectId=" + projectId +
                ", calculateId=" + calculateId +
                ", makerId=" + makerId +
                ", makerUsername='" + makerUsername + '\'' +
                ", makerName='" + makerName + '\'' +
                ", projectName='" + projectName + '\'' +
                ", calculateRound='" + calculateRound + '\'' +
                ", calculateStatus='" + calculateStatus + '\'' +
                ", calculateRegDate=" + calculateRegDate +
                '}';
    }
}
