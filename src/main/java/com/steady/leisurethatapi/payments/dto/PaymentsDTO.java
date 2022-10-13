package com.steady.leisurethatapi.payments.dto;

import java.util.Date;

public class PaymentsDTO {

    private int projectId;
    private String username;
    private int deliveryId;

    private int reward;
    private String authKey;
    private String customerkey;

    public PaymentsDTO() {
        super();
    }

    public PaymentsDTO(int projectId, String username, int deliveryId, int reward, String authKey, String customerkey) {
        this.projectId = projectId;
        this.username = username;
        this.deliveryId = deliveryId;
        this.reward = reward;
        this.authKey = authKey;
        this.customerkey = customerkey;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(int deliveryId) {
        this.deliveryId = deliveryId;
    }

    public int getReward() {
        return reward;
    }

    public void setReward(int reward) {
        this.reward = reward;
    }

    public String getAuthKey() {
        return authKey;
    }

    public void setAuthKey(String authKey) {
        this.authKey = authKey;
    }

    public String getCustomerkey() {
        return customerkey;
    }

    public void setCustomerkey(String customerkey) {
        this.customerkey = customerkey;
    }

    @Override
    public String toString() {
        return "PaymentsDTO{" +
                "projectId=" + projectId +
                ", username='" + username + '\'' +
                ", deliveryId=" + deliveryId +
                ", reward=" + reward +
                ", authKey='" + authKey + '\'' +
                ", customerkey='" + customerkey + '\'' +
                '}';
    }
}
