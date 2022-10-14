package com.steady.leisurethatapi.order.dto;

import lombok.*;

import java.sql.Date;

@Data
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderCompleteDTO {
    private int orderId;
    private String sponserName;
    private String orderStatus;
    private int paymentPrice;
    private String rewardName;
    private String deliveryStatus;
    private Date deliveryDate;
    private Long waybillId;
    private Long courierId;

}
