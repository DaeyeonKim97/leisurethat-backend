package com.steady.leisurethatapi.project.manage.dto;

import com.steady.leisurethatapi.database.entity.Payment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentResponseDTO {
    private int paymentId;
    private String paymentDivision;
    private int paymentPrice;
    private int paymentCount;
    private String paymentStatus;
    private Date paymentDate;
    private OrderResponseDTO order;
    private Date paymentModifyDate;
    private String kakaoPaymentToken;
    private String cardPaymentToken;
    private String refundReason;
    private Date refundRequestDate;
    private Date refundDate;

    public PaymentResponseDTO(Payment payment) {
        this.paymentId = payment.getPaymentId();
        this.paymentDivision = payment.getPaymentDivision();
        this.paymentPrice = payment.getPaymentPrice();
        this.paymentCount = payment.getPaymentCount();
        this.paymentStatus = payment.getPaymentStatus();
        this.paymentDate = payment.getPayementDate();
        this.order = new OrderResponseDTO(payment.getOrder());
        this.paymentModifyDate = payment.getPaymentModifyDate();
        this.kakaoPaymentToken = payment.getKakaoToken();
        this.cardPaymentToken = payment.getCardToken();
        this.refundReason = payment.getRefundReason();
        this.refundRequestDate = payment.getRefundRequestDate();
        this.refundDate = payment.getRefundDate();
    }
}
