package com.steady.leisurethatapi.order.dto;

/**
 * <pre>
 * Class : OrderUserInfoDTO
 * Comment: 주문 내역 후원자 정보 DTO
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-10-05       전현정           최초 생성
 * </pre>
 *
 * @author 전현정(최초 작성자)
 * @version 1(클래스 버전)
 * @see
 */
public class OrderUserInfoDTO {

    private String name;
    private String userName;
    private int orderId;
    private String basicAddress;
    private String detailAddress;
    private String phone;

    public OrderUserInfoDTO() {}

    public OrderUserInfoDTO(String name, String userName, int orderId, String basicAddress, String detailAddress, String phone) {
        this.name = name;
        this.userName = userName;
        this.orderId = orderId;
        this.basicAddress = basicAddress;
        this.detailAddress = detailAddress;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "OrderUserInfoDTO{" +
                "name='" + name + '\'' +
                ", userName='" + userName + '\'' +
                ", orderId=" + orderId +
                ", basicAddress='" + basicAddress + '\'' +
                ", detailAddress='" + detailAddress + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
