package com.steady.leisurethatapi.payments.service;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.net.Socket;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Base64;

@Configuration
public class TossPayment {

    @Value("${key.SECRET}")
    String SECRETKEY;
    @Value("${key.CUSTMER}")
    String CUSTOMER;
    //시크릿키 인코딩

    //예약 결제 키 발급
    public String Authkey(String authKey) throws IOException, InterruptedException, ParseException {
        System.out.println("====customer========");
        System.out.println(CUSTOMER);
        //결제 예약키 발급 요청
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.tosspayments.com/v1/billing/authorizations/"+authKey))
                .header("Authorization", "Basic dGVzdF9za19ENHlLZXE1YmdycG1vT2FSTHlYOEdYMGx6VzZZOg==")
                .header("Content-Type", "application/json")
                .method("POST", HttpRequest.BodyPublishers.ofString("{\"customerKey\":\""+CUSTOMER+"\"}"))
                .build();

        //응답 객체
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        // String 으로 넘어와서 json으로 변경
        JSONParser jsonParse = new JSONParser();
        Object obj = jsonParse.parse(response.body());
        JSONObject jsonObject = (JSONObject)obj;
        return (String) jsonObject.get("billingKey");
    }

    //결제 승인
    public JSONObject BillingKey( String billingKey, int amount, int orderId, String email, String customerName,String orderName,Integer taxFreeAmount ) throws IOException, InterruptedException, ParseException {

        String encoderKey = Base64.getEncoder().encodeToString(SECRETKEY.getBytes());

        // 결제예약 승인
        HttpRequest paymentRequest = HttpRequest.newBuilder()
                .uri(URI.create("https://api.tosspayments.com/v1/billing/"+billingKey))
                .header("Authorization","Basic "+encoderKey)
                .header("Content-Type", "application/json")
                .method("POST", HttpRequest.BodyPublishers.ofString("{" +
                        "\"amount\":\""+amount+"\"," +
                        "\"customerKey\":\""+CUSTOMER+"\"," +
                        "\"orderId\":\""+orderId+"\"," +
                        "\"customerEmail\":\""+email+"\"," +
                        "\"customerName\":\""+customerName+"\"," +
                        "\"orderName\":\""+orderName+"\"," +
                        "\"taxFreeAmount\":\""+taxFreeAmount+"\"" +
                        "}"))
                .build();

        HttpResponse<String> response = HttpClient.newHttpClient().send(paymentRequest, HttpResponse.BodyHandlers.ofString());

        // String 으로 넘어와서 json으로 변경
        JSONParser jsonParse = new JSONParser();
        Object obj = jsonParse.parse(response.body());
        JSONObject jsonObject = (JSONObject)obj;

        return jsonObject;
    }


}
