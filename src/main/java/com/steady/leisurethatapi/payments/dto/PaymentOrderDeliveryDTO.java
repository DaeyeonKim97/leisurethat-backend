package com.steady.leisurethatapi.payments.dto;

import com.steady.leisurethatapi.database.entity.OrderDelivery;
import com.steady.leisurethatapi.database.entity.Payment;

public class PaymentOrderDeliveryDTO {

    private Payment payment;
    private OrderDelivery orderDelivery;

    public Payment getPayment() {
        return payment;
    }

    public PaymentOrderDeliveryDTO(Payment payment, OrderDelivery orderDelivery) {
        this.payment = payment;
        this.orderDelivery = orderDelivery;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public OrderDelivery getOrderDelivery() {
        return orderDelivery;
    }

    public void setOrderDelivery(OrderDelivery orderDelivery) {
        this.orderDelivery = orderDelivery;
    }

    @Override
    public String toString() {
        return "PaymentOrderDeliveryDTO{" +
                "payment=" + payment +
                ", orderDelivery=" + orderDelivery +
                '}';
    }
}
