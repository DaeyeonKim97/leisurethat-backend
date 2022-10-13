package com.steady.leisurethatapi.payments.controller;

import com.steady.leisurethatapi.common.dto.ResponseMessage;
import com.steady.leisurethatapi.database.entity.*;
import com.steady.leisurethatapi.database.repository.*;
import com.steady.leisurethatapi.payments.dto.PaymentOrderDeliveryDTO;
import com.steady.leisurethatapi.payments.dto.PaymentsDTO;
import com.steady.leisurethatapi.payments.service.PaymentService;
import com.steady.leisurethatapi.payments.service.TossPayment;
import org.json.simple.parser.ParseException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.io.IOException;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentService paymentService;
    private final ProjectRepository projectRepository;
    private final RewardRepository rewardRepository;
    private final MemberRepository memberRepository;
    private final DeliveryRepository deliveryRepository;
    private final OrderRepository orderRepository;
    private final OrderDeliveryRepository orderDeliveryRepository;
    private final PaymentRepository paymentRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final TossPayment tossPayment;

    public PaymentController(PaymentService paymentService,
                             ProjectRepository projectRepository,
                             RewardRepository rewardRepository,
                             MemberRepository memberRepository,
                             DeliveryRepository deliveryRepository,
                             OrderRepository orderRepository,
                             OrderDeliveryRepository orderDeliveryRepository,
                             PaymentRepository paymentRepository,
                             OrderDetailRepository orderDetailRepository,
                             TossPayment tossPayment){
        this.paymentService = paymentService;
        this.projectRepository = projectRepository;
        this.rewardRepository = rewardRepository;
        this.memberRepository = memberRepository;
        this.deliveryRepository = deliveryRepository;
        this.orderRepository = orderRepository;
        this.orderDeliveryRepository = orderDeliveryRepository;
        this.paymentRepository = paymentRepository;
        this.orderDetailRepository = orderDetailRepository;
        this.tossPayment = tossPayment;
    }

    @GetMapping
    public ResponseEntity<?> paymentList(){
       List<Payment>  paymentList = paymentRepository.findAll();

       List<PaymentOrderDeliveryDTO> payDeliveryList = new ArrayList<>();
       for (Payment pay: paymentList) {
           payDeliveryList.add(new PaymentOrderDeliveryDTO(pay,orderDeliveryRepository.findByOrder(pay.getOrder()))) ;
       }

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("payments", payDeliveryList);
        return ResponseEntity.ok().body(new ResponseMessage(200,"success", resultMap));
    }

    @GetMapping("/{rewardId}")
    public ResponseEntity<?> paymentPage(@PathVariable(value = "rewardId") int rewardId){

        String username = null;
        try{
            username = SecurityContextHolder.getContext().getAuthentication().getName();
        } catch (Exception e){
            return ResponseEntity
                    .badRequest()
                    .build();
        }
        Member user = memberRepository.findByUsername(username);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        Map<String, Object> responseMap = new HashMap<>();

        Reward reward = rewardRepository.findById(rewardId);
        responseMap.put("reward",reward);
        responseMap.put("project",projectRepository.findById(reward.getProject().getId()));
        responseMap.put("user",user);
        responseMap.put("delivery",deliveryRepository.findByDeliveryBasicAndMember("Y", user));

        System.out.println("=============Delivery========");
        System.out.println(deliveryRepository.findByDeliveryBasicAndMember("Y", user));
        return ResponseEntity.ok().body(new ResponseMessage(200, "success", responseMap));
    }
    @Transactional
    @PostMapping
    public String tossPay(@RequestBody PaymentsDTO paymentsDTO) throws IOException, InterruptedException, ParseException {


        System.out.println("========pay start========");
        //빌링키 인증 필요
        if(paymentsDTO.getCustomerkey() == null){
            return "인증키가 유효하지 않습니다.";
        }
        if(paymentsDTO.getAuthKey() == null){
            return "authKey가 유효하지 않습니다.";
        }
        if(paymentsDTO.getUsername() == null){
            return "회원 정보가 유효하지 않습니다.";
        }
        if(paymentsDTO.getProjectId() == 0){
            return "프로젝트 정보가 유효하지 않습니다.";
        }
        if (paymentsDTO.getDeliveryId() == 0) {
            return "배송지 정보가 유효하지 않습니다.";
        }
        if(paymentsDTO.getReward() == 0){
            return "리워드 정보가 유효하지 않습니다.";
        }



        Date today = new Date();

        Member member = memberRepository.findByUsername(paymentsDTO.getUsername());
        Project project = projectRepository.findById(paymentsDTO.getProjectId());
        Reward reward = rewardRepository.findById(paymentsDTO.getReward());

        // 주문 등록
        Order order = new Order();
        order.setMember(member);
        order.setOrderDate(new java.sql.Date(today.getTime()));
        order.setStatus("예약 완료");
        order.setProject(project);
        order.setReward(reward);

        int orderId = orderRepository.save(order).getId();
        Order newOrder = orderRepository.findById(orderId);

        // 상세 주문 등록
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setRewardAmount(1);
        orderDetail.setReward(reward);
        orderDetail.setOrder(newOrder);
        orderDetail.setOrderDetailStatus("예약 완료");
        orderDetailRepository.save(orderDetail);

        int price = 0;
        if(reward.getPrice() == null){
            price = reward.getRewardFee();
        }else if(reward.getRewardFee() == 0){
            price = reward.getRewardFeeFar()+ reward.getPrice();
        }else{
            price = reward.getPrice()+ reward.getRewardFee();
        }
        String billingKey = tossPayment.Authkey(paymentsDTO.getAuthKey());
        if(billingKey == null){
            return "카드가 유효하지 않습니다.";
        }
        // 결제 등록
        Payment payment = new Payment();
        payment.setDivision("C");
        payment.setPrice(price);
        payment.setConunt(0);
        payment.setPaymentState("결제 대기");
        payment.setPaymentReserveDate(new java.sql.Date(today.getTime()));
        payment.setOrder(newOrder);
        payment.setBillingKey(billingKey);
        payment.setPaymentToken(paymentsDTO.getCustomerkey());
        paymentRepository.save(payment);

        // 주문배송 등록
        Delivery delivery = deliveryRepository.getReferenceById(paymentsDTO.getDeliveryId());

        OrderDelivery orderDelivery = new OrderDelivery();
        orderDelivery.setDelivery(delivery);
        orderDelivery.setDeliveryState("배송대기");
        orderDelivery.setDeliveryRegistDate(new java.sql.Date(today.getTime()));
        orderDelivery.setOrder(newOrder);
        orderDelivery.setMember(member);

        orderDeliveryRepository.save(orderDelivery);
        return "test";
    }


}
























