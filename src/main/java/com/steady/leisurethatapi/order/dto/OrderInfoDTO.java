package com.steady.leisurethatapi.order.dto;

/**
 * <pre>
 * Class : OrderInfo
 * Comment: OrderInfo DTO
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-10-04       전현정           최초 생성
 * </pre>
 *
 * @author 전현정(최초 작성자)
 * @version 1(클래스 버전)
 * @see
 */
public class OrderInfoDTO {

    private int orderId;
    private String sponserName;
    private String orderStatus;
    private int paymentPrice;
    private String rewardName;

    public OrderInfoDTO() {}

    public OrderInfoDTO(int orderId, String sponserName, String orderStatus, int paymentPrice, String rewardName) {
        this.orderId = orderId;
        this.sponserName = sponserName;
        this.orderStatus = orderStatus;
        this.paymentPrice = paymentPrice;
        this.rewardName = rewardName;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getSponserName() {
        return sponserName;
    }

    public void setSponserName(String sponserName) {
        this.sponserName = sponserName;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public int getPaymentPrice() {
        return paymentPrice;
    }

    public void setPaymentPrice(int paymentPrice) {
        this.paymentPrice = paymentPrice;
    }

    public String getRewardName() {
        return rewardName;
    }

    public void setRewardName(String rewardName) {
        this.rewardName = rewardName;
    }

    @Override
    public String toString() {
        return "OrderInfoDTO{" +
                "orderId=" + orderId +
                ", sponserName='" + sponserName + '\'' +
                ", orderStatus='" + orderStatus + '\'' +
                ", paymentPrice=" + paymentPrice +
                ", rewardName='" + rewardName + '\'' +
                '}';
    }
}
