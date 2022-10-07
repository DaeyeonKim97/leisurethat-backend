package com.steady.leisurethatapi.calculate.dto;

/**
 * <pre>
 * Class : CalculateApplicationResponseDTO
 * Comment: 클래스에 대한 간단 설명
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
public class CalculateApplicationResponseDTO {

    private String projectName;
    private String makerUserName;
    private String calculateRound;
    private String calculateStatus;
    private String category;
    private String atcDownload;             //1차
    private long deliveryCompleteCount;     //배송완료
    private long deliveryOngoingCount;      //배송중
    private long deliveryOnCallCount;       //배송대기중
    private long totalDeliveryCount;        //총 배송 건수
    private long calculateAmount;

    public CalculateApplicationResponseDTO() {}

    public CalculateApplicationResponseDTO(String projectName, String makerUserName, String calculateRound, String calculateStatus, String category, String atcDownload, long deliveryCompleteCount, long deliveryOngoingCount, long deliveryOnCallCount, long totalDeliveryCount, long calculateAmount) {
        this.projectName = projectName;
        this.makerUserName = makerUserName;
        this.calculateRound = calculateRound;
        this.calculateStatus = calculateStatus;
        this.category = category;
        this.atcDownload = atcDownload;
        this.deliveryCompleteCount = deliveryCompleteCount;
        this.deliveryOngoingCount = deliveryOngoingCount;
        this.deliveryOnCallCount = deliveryOnCallCount;
        this.totalDeliveryCount = totalDeliveryCount;
        this.calculateAmount = calculateAmount;
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

    public long getDeliveryCompleteCount() {
        return deliveryCompleteCount;
    }

    public void setDeliveryCompleteCount(long deliveryCompleteCount) {
        this.deliveryCompleteCount = deliveryCompleteCount;
    }

    public long getDeliveryOngoingCount() {
        return deliveryOngoingCount;
    }

    public void setDeliveryOngoingCount(long deliveryOngoingCount) {
        this.deliveryOngoingCount = deliveryOngoingCount;
    }

    public long getDeliveryOnCallCount() {
        return deliveryOnCallCount;
    }

    public void setDeliveryOnCallCount(long deliveryOnCallCount) {
        this.deliveryOnCallCount = deliveryOnCallCount;
    }

    public long getTotalDeliveryCount() {
        return totalDeliveryCount;
    }

    public void setTotalDeliveryCount(long totalDeliveryCount) {
        this.totalDeliveryCount = totalDeliveryCount;
    }

    public long getCalculateAmount() {
        return calculateAmount;
    }

    public void setCalculateAmount(long calculateAmount) {
        this.calculateAmount = calculateAmount;
    }

    @Override
    public String toString() {
        return "CalculateApplicationResponseDTO{" +
                "projectName='" + projectName + '\'' +
                ", makerUserName='" + makerUserName + '\'' +
                ", calculateRound='" + calculateRound + '\'' +
                ", calculateStatus='" + calculateStatus + '\'' +
                ", category='" + category + '\'' +
                ", atcDownload='" + atcDownload + '\'' +
                ", deliveryCompleteCount=" + deliveryCompleteCount +
                ", deliveryOngoingCount=" + deliveryOngoingCount +
                ", deliveryOnCallCount=" + deliveryOnCallCount +
                ", totalDeliveryCount=" + totalDeliveryCount +
                ", calculateAmount=" + calculateAmount +
                '}';
    }
}
