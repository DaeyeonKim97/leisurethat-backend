package com.steady.leisurethatapi.payments.service;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.UUID;


@Service
public class PaymentService {

    private final TossPayment tossPayment;

    public PaymentService(TossPayment tossPayment){
        this.tossPayment = tossPayment;
    }

    public void ReservationPayment(String customerKey, String authKey) throws IOException, InterruptedException, ParseException {

        String resultKey = tossPayment.Authkey(customerKey,authKey);
        UUID Testid = UUID.randomUUID();
        int amount = 100;
        String orderId = Testid.toString();
        String email = "test@mail.com";
        String customerName = "이상우";
        String orderName = "테스트";
        int taxFreeAmount = 0;

        //결제 진행됨
        JSONObject test = tossPayment.BillingKey(customerKey,resultKey,amount, orderId, email,customerName,orderName, taxFreeAmount);


        System.out.println("=====service======");
        System.out.println(test);

    }
}
