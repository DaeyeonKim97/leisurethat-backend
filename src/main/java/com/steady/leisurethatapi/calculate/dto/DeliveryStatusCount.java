package com.steady.leisurethatapi.calculate.dto;

/**
 * <pre>
 * Class : DeliveryStatusCount
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
public class DeliveryStatusCount {
    
    private String deliveryStatus;
    private long deliveryStatusCount;

    public DeliveryStatusCount() {}

    public DeliveryStatusCount(String deliveryStatus, long deliveryStatusCount) {
        this.deliveryStatus = deliveryStatus;
        this.deliveryStatusCount = deliveryStatusCount;
    }

    public String getDeliveryStatus() {
        return deliveryStatus;
    }
    
    public void setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public long getDeliveryStatusCount() {
        return deliveryStatusCount;
    }

    public void setDeliveryStatusCount(long deliveryStatusCount) {
        this.deliveryStatusCount = deliveryStatusCount;
    }

    @Override
    public String toString() {
        return "DeliveryStatusCount{" +
                "deliveryStatus='" + deliveryStatus + '\'' +
                ", deliveryStatusCount=" + deliveryStatusCount +
                '}';
    }
}
