package com.steady.leisurethatapi.project.manage.dto;

import com.steady.leisurethatapi.database.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponseDTO {
    private int orderId;
    private MemberResponseDTO orderMember;
    private RewardResponseDTO orderReward;
    private Date orderDate;
    private String orderStatus;

    public OrderResponseDTO(Order order){
        this.orderId = order.getId();
        this.orderMember = new MemberResponseDTO(order.getMember());
        this.orderReward = new RewardResponseDTO(order.getReward());
        this.orderDate = order.getOrderDate();
        this.orderStatus = order.getStatus();
    }
}
