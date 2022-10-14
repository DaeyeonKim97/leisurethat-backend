package com.steady.leisurethatapi.project.main.dto;

import com.steady.leisurethatapi.database.entity.Payment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentSummaryDTO {
    private int totalParticipant;
    private int amount;

    public PaymentSummaryDTO(List<Payment> paymentList){
        totalParticipant = 0;
        amount = 0;

        for(Payment payment : paymentList){
            totalParticipant += 1;
            amount += payment.getPaymentPrice();
        }
    }
}
