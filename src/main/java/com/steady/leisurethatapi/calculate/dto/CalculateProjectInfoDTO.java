package com.steady.leisurethatapi.calculate.dto;

import java.sql.Date;

/**
 * <pre>
 * Class : CalculateProjectInfoDTO
 * Comment:
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
public class CalculateProjectInfoDTO {

    private String makerName;
    private String makerEmail;
    private String makerPhone;
    private String projectName;
    private String projectCategory;
    private String projectImg;
    private Date projectStartDate;
    private Date projectEndDate;
    private long actualAmount;          //실 결제 금액
    private long totalAmount;           //전체 금액
    private long preAmount;             //1차 정산 신청 금액
    private Date preAmountGiveDate;     //1차 정산 지급일
    private long postAmount;            //2차 정산 신청 금액
    private Date postAmountGiveDate;    //2차 정산 지급일
    private long totalCalculateAmount;  //총 정산 금액
    private int achievement;            //프로젝트 달성률
    private int supportCount;           //프로젝트 주문 건수

    public CalculateProjectInfoDTO() {}

    public CalculateProjectInfoDTO(String makerName, String makerEmail, String makerPhone, String projectName, String projectCategory, String projectImg, Date projectStartDate, Date projectEndDate, long actualAmount, long totalAmount, long preAmount, Date preAmountGiveDate, long postAmount, Date postAmountGiveDate, long totalCalculateAmount, int achievement, int supportCount) {
        this.makerName = makerName;
        this.makerEmail = makerEmail;
        this.makerPhone = makerPhone;
        this.projectName = projectName;
        this.projectCategory = projectCategory;
        this.projectImg = projectImg;
        this.projectStartDate = projectStartDate;
        this.projectEndDate = projectEndDate;
        this.actualAmount = actualAmount;
        this.totalAmount = totalAmount;
        this.preAmount = preAmount;
        this.preAmountGiveDate = preAmountGiveDate;
        this.postAmount = postAmount;
        this.postAmountGiveDate = postAmountGiveDate;
        this.totalCalculateAmount = totalCalculateAmount;
        this.achievement = achievement;
        this.supportCount = supportCount;
    }

    public String getMakerName() {
        return makerName;
    }

    public void setMakerName(String makerName) {
        this.makerName = makerName;
    }

    public String getMakerEmail() {
        return makerEmail;
    }

    public void setMakerEmail(String makerEmail) {
        this.makerEmail = makerEmail;
    }

    public String getMakerPhone() {
        return makerPhone;
    }

    public void setMakerPhone(String makerPhone) {
        this.makerPhone = makerPhone;
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

    public String getProjectImg() {
        return projectImg;
    }

    public void setProjectImg(String projectImg) {
        this.projectImg = projectImg;
    }

    public Date getProjectStartDate() {
        return projectStartDate;
    }

    public void setProjectStartDate(Date projectStartDate) {
        this.projectStartDate = projectStartDate;
    }

    public Date getProjectEndDate() {
        return projectEndDate;
    }

    public void setProjectEndDate(Date projectEndDate) {
        this.projectEndDate = projectEndDate;
    }

    public long getActualAmount() {
        return actualAmount;
    }

    public void setActualAmount(long actualAmount) {
        this.actualAmount = actualAmount;
    }

    public long getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(long totalAmount) {
        this.totalAmount = totalAmount;
    }

    public long getPreAmount() {
        return preAmount;
    }

    public void setPreAmount(long preAmount) {
        this.preAmount = preAmount;
    }

    public Date getPreAmountGiveDate() {
        return preAmountGiveDate;
    }

    public void setPreAmountGiveDate(Date preAmountGiveDate) {
        this.preAmountGiveDate = preAmountGiveDate;
    }

    public long getPostAmount() {
        return postAmount;
    }

    public void setPostAmount(long postAmount) {
        this.postAmount = postAmount;
    }

    public Date getPostAmountGiveDate() {
        return postAmountGiveDate;
    }

    public void setPostAmountGiveDate(Date postAmountGiveDate) {
        this.postAmountGiveDate = postAmountGiveDate;
    }

    public long getTotalCalculateAmount() {
        return totalCalculateAmount;
    }

    public void setTotalCalculateAmount(long totalCalculateAmount) {
        this.totalCalculateAmount = totalCalculateAmount;
    }

    public int getAchievement() {
        return achievement;
    }

    public void setAchievement(int achievement) {
        this.achievement = achievement;
    }

    public int getSupportCount() {
        return supportCount;
    }

    public void setSupportCount(int supportCount) {
        this.supportCount = supportCount;
    }

    @Override
    public String toString() {
        return "CalculateProjectInfoDTO{" +
                "makerName='" + makerName + '\'' +
                ", makerEmail='" + makerEmail + '\'' +
                ", makerPhone='" + makerPhone + '\'' +
                ", projectName='" + projectName + '\'' +
                ", projectCategory='" + projectCategory + '\'' +
                ", projectImg='" + projectImg + '\'' +
                ", projectStartDate=" + projectStartDate +
                ", projectEndDate=" + projectEndDate +
                ", actualAmount=" + actualAmount +
                ", totalAmount=" + totalAmount +
                ", preAmount=" + preAmount +
                ", preAmountGiveDate=" + preAmountGiveDate +
                ", postAmount=" + postAmount +
                ", postAmountGiveDate=" + postAmountGiveDate +
                ", totalCalculateAmount=" + totalCalculateAmount +
                ", achievement=" + achievement +
                ", supportCount=" + supportCount +
                '}';
    }
}
