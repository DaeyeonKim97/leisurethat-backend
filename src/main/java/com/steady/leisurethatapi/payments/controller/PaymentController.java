package com.steady.leisurethatapi.payments.controller;

import com.steady.leisurethatapi.payments.service.PaymentService;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.http.HttpRequest;
import java.util.Map;

@RestController
@RequestMapping("/payment")
public class PaymentController {


    final PaymentService paymentService;

    public PaymentController(PaymentService paymentService){
        this.paymentService = paymentService;
    }

    @GetMapping("/tosspay")
    public String payment(@RequestParam(value = "customerKey") String customerKey,
                          @RequestParam(value = "authKey") String authKey) throws IOException, ParseException, InterruptedException {

        //빌링키 인증 필요
        if(customerKey == null){
            return "인증키가 유효하지 않습니다.";
        }
        if(authKey == null){
            return "authKey가 유효하지 않습니다.";
        }

        //토스 결제 예약 프로세스
        paymentService.ReservationPayment(customerKey, authKey);


        return "test";
    }
}