package com.steady.leisurethatapi.user.dto;

import java.sql.Date;

/**
 * <pre>
 * Class : FundingResponseDTO
 * Comment: 클래스에 대한 간단 설명
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
public class FundingResponseDTO {

    private String paymentStatus;
    private String projectName;
    private int achievement;
    private Date orderDate;
    private Date projectEndDate;
    private int orderId;
    private String rewardName;
    private String rewardOption;
    private int rewardAmount;
    private String receiver;
    private String receiverPhone;
    private String basicAddress;
    private String detailAddress;
    private String paymentInfo;
    private long paymentPrice;

    public FundingResponseDTO() {}

    public FundingResponseDTO(String paymentStatus, String projectName, int achievement, Date orderDate, Date projectEndDate, int orderId, String rewardName, String rewardOption, int rewardAmount, String receiver, String receiverPhone, String basicAddress, String detailAddress, String paymentInfo, long paymentPrice) {
        this.paymentStatus = paymentStatus;
        this.projectName = projectName;
        this.achievement = achievement;
        this.orderDate = orderDate;
        this.projectEndDate = projectEndDate;
        this.orderId = orderId;
        this.rewardName = rewardName;
        this.rewardOption = rewardOption;
        this.rewardAmount = rewardAmount;
        this.receiver = receiver;
        this.receiverPhone = receiverPhone;
        this.basicAddress = basicAddress;
        this.detailAddress = detailAddress;
        this.paymentInfo = paymentInfo;
        this.paymentPrice = paymentPrice;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
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

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getProjectEndDate() {
        return projectEndDate;
    }

    public void setProjectEndDate(Date projectEndDate) {
        this.projectEndDate = projectEndDate;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getRewardName() {
        return rewardName;
    }

    public void setRewardName(String rewardName) {
        this.rewardName = rewardName;
    }

    public String getRewardOption() {
        return rewardOption;
    }

    public void setRewardOption(String rewardOption) {
        this.rewardOption = rewardOption;
    }

    public int getRewardAmount() {
        return rewardAmount;
    }

    public void setRewardAmount(int rewardAmount) {
        this.rewardAmount = rewardAmount;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }

    public String getBasicAddress() {
        return basicAddress;
    }

    public void setBasicAddress(String basicAddress) {
        this.basicAddress = basicAddress;
    }

    public String getDetailAddress() {
        return detailAddress;
    }

    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress;
    }

    public String getPaymentInfo() {
        return paymentInfo;
    }

    public void setPaymentInfo(String paymentInfo) {
        this.paymentInfo = paymentInfo;
    }

    public long getPaymentPrice() {
        return paymentPrice;
    }

    public void setPaymentPrice(long paymentPrice) {
        this.paymentPrice = paymentPrice;
    }

    @Override
    public String toString() {
        return "FundingResponseDTO{" +
                "paymentStatus='" + paymentStatus + '\'' +
                ", projectName='" + projectName + '\'' +
                ", achievement=" + achievement +
                ", orderDate=" + orderDate +
                ", projectEndDate=" + projectEndDate +
                ", orderId=" + orderId +
                ", rewardName='" + rewardName + '\'' +
                ", rewardOption='" + rewardOption + '\'' +
                ", rewardAmount=" + rewardAmount +
                ", receiver='" + receiver + '\'' +
                ", receiverPhone='" + receiverPhone + '\'' +
                ", basicAddress='" + basicAddress + '\'' +
                ", detailAddress='" + detailAddress + '\'' +
                ", paymentInfo='" + paymentInfo + '\'' +
                ", paymentPrice=" + paymentPrice +
                '}';
    }
}
