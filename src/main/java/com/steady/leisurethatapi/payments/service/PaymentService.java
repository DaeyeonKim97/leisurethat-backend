package com.steady.leisurethatapi.payments.service;

import com.steady.leisurethatapi.database.entity.Member;
import com.steady.leisurethatapi.database.entity.Order;
import com.steady.leisurethatapi.database.entity.Payment;
import com.steady.leisurethatapi.database.entity.Project;
import com.steady.leisurethatapi.database.repository.MemberRepository;
import com.steady.leisurethatapi.database.repository.OrderRepository;
import com.steady.leisurethatapi.database.repository.PaymentRepository;
import com.steady.leisurethatapi.database.repository.ProjectRepository;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@Service
public class PaymentService {

    private final TossPayment tossPayment;
    private final ProjectRepository projectRepository;
    private final OrderRepository orderRepository;
    private final PaymentRepository paymentRepository;
    private final MemberRepository memberRepository;

    public PaymentService(TossPayment tossPayment,
                          ProjectRepository projectRepository,
                          OrderRepository orderRepository,
                          PaymentRepository paymentRepository,
                          MemberRepository memberRepository){
        this.tossPayment = tossPayment;
        this.projectRepository = projectRepository;
        this.orderRepository = orderRepository;
        this.paymentRepository = paymentRepository;
        this.memberRepository = memberRepository;
    }

    @Scheduled(fixedDelay = 1000000)
    public void ReservationPayment() throws IOException, InterruptedException, ParseException, java.text.ParseException {
        Date utileDate = new Date();
        java.sql.Date whereDate = new java.sql.Date(utileDate.getTime());
        List<Project> project = projectRepository.findByEndDate(whereDate);
        if(project.size() > 0){
            for (Project pro: project) {
                List<Order> orderList = orderRepository.findByProjectId(pro.getId());
                for (Order order: orderList) {
                    if(!order.getStatus().equals("?????? ??????")){
                        Payment payment = paymentRepository.findByOrderAndCardTokenIsNotNull(order);
                        if(payment != null){
                            JSONObject payRequest = tossPayment.BillingKey(payment.getCardToken(),payment.getPaymentPrice(),payment.getPaymentId(),order.getMember().getEmail(), order.getMember().getName(),order.getReward().getTitle(),0 );
                            if(payRequest.get("code") == null) {
                                payment.setPaymentStatus("?????? ??????");
                                payment.setPaymentCount(payment.getPaymentCount() + 1);
                                payment.setPaymentModifyDate(whereDate);
                            }else{
                                payment.setPayementDate(whereDate);
                                payment.setPaymentStatus("?????? ??????");
                            }
                            paymentRepository.save(payment);
                        }
                    }
                }
            }
        }
    }
}
