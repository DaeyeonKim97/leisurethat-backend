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
    //시크릿키 인코딩

    //예약 결제 키 발급
    public String Authkey(String customerKey, String authKey) throws IOException, InterruptedException, ParseException {

        //결제 예약키 발급 요청
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.tosspayments.com/v1/billing/authorizations/"+authKey))
                .header("Authorization", "Basic dGVzdF9za19ENHlLZXE1YmdycG1vT2FSTHlYOEdYMGx6VzZZOg==")
                .header("Content-Type", "application/json")
                .method("POST", HttpRequest.BodyPublishers.ofString("{\"customerKey\":\""+customerKey+"\"}"))
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
    public JSONObject BillingKey(String customerKey, String billingKey, int amount, String orderId, String email, String customerName,String orderName,int taxFreeAmount ) throws IOException, InterruptedException, ParseException {

        String encoderKey = Base64.getEncoder().encodeToString(SECRETKEY.getBytes());

        // 결제예약 승인
        HttpRequest paymentRequest = HttpRequest.newBuilder()
                .uri(URI.create("https://api.tosspayments.com/v1/billing/"+billingKey))
                .header("Authorization","Basic "+encoderKey)
                .header("Content-Type", "application/json")
                .method("POST", HttpRequest.BodyPublishers.ofString("{" +
                        "\"amount\":\""+amount+"\"," +
                        "\"customerKey\":\""+customerKey+"\"," +
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

    public JSONObject paymentApproval(String customerKey,String billingKey,String orderId) throws IOException, InterruptedException, ParseException {

        String encoderKey = Base64.getEncoder().encodeToString(SECRETKEY.getBytes());

        int amount = 100;
        String email = "test@mail.com";
        String customerName = "이상우";
        String orderName = "테스트";
        int taxFreeAmount = 0;

        // 결제 승인
        HttpRequest approvalRequest = HttpRequest.newBuilder()
                .uri(URI.create("https://api.tosspayments.com/v1/billing/"+billingKey))
                .header("Authorization","Basic "+encoderKey)
                .header("Content-Type", "application/json")
                .method("POST", HttpRequest.BodyPublishers.ofString("{" +
                        "\"customerKey\":\""+customerKey+"\"," +
                        "\"amount\":\""+amount+"\"," +
                        "\"orderId\":\""+orderId+"\"," +
                        "\"orderName\":\""+orderName+"\"," +
                        "\"customerEmail\":\""+email+"\"," +
                        "\"customerName\":\""+customerName+"\"," +
                        "\"taxFreeAmount\":\""+taxFreeAmount+"\"" +
                        "}"))
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(approvalRequest, HttpResponse.BodyHandlers.ofString());

        // String 으로 넘어와서 json으로 변경
        JSONParser jsonParse = new JSONParser();
        Object obj = jsonParse.parse(response.body());
        JSONObject jsonObject = (JSONObject)obj;

        return jsonObject;

    }

}
