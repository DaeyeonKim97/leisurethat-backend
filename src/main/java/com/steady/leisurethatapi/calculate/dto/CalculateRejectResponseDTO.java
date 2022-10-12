package com.steady.leisurethatapi.calculate.dto;

import com.steady.leisurethatapi.database.entity.Atc;

import java.util.List;

/**
 * <pre>
 * Class : CalculateRejectResponseDTO
 * Comment: 정산 심사 반려 사유 response dto
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-10-07       홍길동           최초 생성
 * </pre>
 *
 * @author 홍길동(최초 작성자)
 * @version 1(클래스 버전)
 * @see
 */
public class CalculateRejectResponseDTO {

    private String projectName;
    private String makerUserName;
    private String category;
    private String atcDownload;
    private int judgeId;
    private int rejectId;
    private String rejectTitle;
    private String rejectContent;
    public CalculateRejectResponseDTO() {}

    public CalculateRejectResponseDTO(String projectName, String makerUserName, String category, String atcDownload, int judgeId, int rejectId, String rejectTitle, String rejectContent) {
        this.projectName = projectName;
        this.makerUserName = makerUserName;
        this.category = category;
        this.atcDownload = atcDownload;
        this.judgeId = judgeId;
        this.rejectId = rejectId;
        this.rejectTitle = rejectTitle;
        this.rejectContent = rejectContent;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getMakerUserName() {
        return makerUserName;
    }

    public void setMakerUserName(String makerUserName) {
        this.makerUserName = makerUserName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAtcDownload() {
        return atcDownload;
    }

    public void setAtcDownload(String atcDownload) {
        this.atcDownload = atcDownload;
    }

    public int getJudgeId() {
        return judgeId;
    }

    public void setJudgeId(int judgeId) {
        this.judgeId = judgeId;
    }

    public int getRejectId() {
        return rejectId;
    }

    public void setRejectId(int rejectId) {
        this.rejectId = rejectId;
    }

    public String getRejectTitle() {
        return rejectTitle;
    }

    public void setRejectTitle(String rejectTitle) {
        this.rejectTitle = rejectTitle;
    }

    public String getRejectContent() {
        return rejectContent;
    }

    public void setRejectContent(String rejectContent) {
        this.rejectContent = rejectContent;
    }

    @Override
    public String toString() {
        return "CalculateRejectResponseDTO{" +
                "projectName='" + projectName + '\'' +
                ", makerUserName='" + makerUserName + '\'' +
                ", category='" + category + '\'' +
                ", atcDownload='" + atcDownload + '\'' +
                ", judgeId=" + judgeId +
                ", rejectId=" + rejectId +
                ", rejectTitle='" + rejectTitle + '\'' +
                ", rejectContent='" + rejectContent + '\'' +
                '}';
    }
}