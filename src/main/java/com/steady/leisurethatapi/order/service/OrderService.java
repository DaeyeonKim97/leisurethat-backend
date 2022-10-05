package com.steady.leisurethatapi.order.service;

import com.steady.leisurethatapi.database.entity.OrderDelivery;
import com.steady.leisurethatapi.database.entity.Payment;
import com.steady.leisurethatapi.database.repository.OrderDeliveryRepositroy;
import com.steady.leisurethatapi.database.repository.PaymentRepository;
import com.steady.leisurethatapi.order.dto.OrderInfoDTO;
import com.steady.leisurethatapi.order.dto.OrderUserInfoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * Class : OrderService
 * Comment: 클래스에 대한 간단 설명
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-10-04       홍길동           최초 생성
 * </pre>
 *
 * @author 홍길동(최초 작성자)
 * @version 1(클래스 버전)
 */
@Service
public class OrderService {

    private final PaymentRepository paymentRepository;
    private final OrderDeliveryRepositroy orderDeliveryRepositroy;

    @Autowired
    public OrderService(PaymentRepository paymentRepository, OrderDeliveryRepositroy orderDeliveryRepositroy) {

        this.paymentRepository = paymentRepository;
        this.orderDeliveryRepositroy = orderDeliveryRepositroy;
    }
    public List<OrderInfoDTO> selectOrderCancleList(int projectId, int id, String sponserName, int offset) {

        String orderStatus = "주문 취소";
        System.out.println("서버 열렸냐?");
        Pageable pageable = PageRequest.of(offset, 6, Sort.by("order.id").descending());
        List<Payment> paymentList = null;
//        paymentList = paymentList = paymentRepository.findAllByOrderProjectIdAndOrderOrderStatus(projectId, orderStatus, pageable);
        if(id > 0 && sponserName != null){
            paymentList = paymentRepository.findAllByOrderProjectIdAndOrderOrderStatusAndOrderIdAndOrderMemberName(projectId, orderStatus, id, sponserName, pageable);
        } else if (id > 0) {
            paymentList = paymentRepository.findAllByOrderProjectIdAndOrderOrderStatusAndOrderId(projectId, orderStatus, id, pageable);
        } else if(sponserName != null) {
            paymentList = paymentRepository.findAllByOrderProjectIdAndOrderOrderStatusAndOrderMemberName(projectId, orderStatus, sponserName, pageable);
        } else {
            paymentList = paymentRepository.findAllByOrderProjectIdAndOrderOrderStatus(projectId, orderStatus, pageable);
        }

        paymentList.forEach(payment -> {
            System.out.println("있니?" + payment);
        });

        List<OrderInfoDTO> cancleList = new ArrayList<>();

        //람다식
        paymentList.forEach(payment -> {
            OrderInfoDTO orderInfo = new OrderInfoDTO();

            orderInfo.setOrderId(payment.getOrder().getId());
            orderInfo.setPaymentPrice(payment.getPaymentPrice());
            orderInfo.setRewardName(payment.getOrder().getReward().getTitle());
            orderInfo.setSponserName(payment.getOrder().getMember().getName());
            orderInfo.setOrderStatus(payment.getOrder().getStatus());

            cancleList.add(orderInfo);
        });

        //이렇게 쓰지 말자
//        for(int i = 0; i < paymentList.size(); i++) {
//            OrderInfoDTO orderInfo = new OrderInfoDTO();
//
//            orderInfo.setOrderId(paymentList.get(i).getOrder().getId());
//            orderInfo.setPaymentPrice(paymentList.get(i).getPaymentPrice());
//            orderInfo.setRewardName(paymentList.get(i).getOrder().getReward().getRewardTitle());
//            orderInfo.setSponserName(paymentList.get(i).getOrder().getMember().getName());
//            orderInfo.setOrderStatus(paymentList.get(i).getOrder().getOrderStatus());
//
//            cancleList.add(orderInfo);
//        }

        return cancleList;


    }

    public OrderUserInfoDTO selectOrderCancleUserInfoByOrderId(int id) {


        OrderDelivery orderCancleUserInfo = orderDeliveryRepositroy.findByOrderId(id);

        System.out.println(orderCancleUserInfo);

        OrderUserInfoDTO orderUserInfo = new OrderUserInfoDTO();

        orderUserInfo.setOrderId(orderCancleUserInfo.getOrder().getId());
        orderUserInfo.setName(orderCancleUserInfo.getMember().getName());
        orderUserInfo.setUserName(orderCancleUserInfo.getMember().getUsername());
        orderUserInfo.setBasicAddress(orderCancleUserInfo.getDelivery().getDeliveryBasicAddress());
        orderUserInfo.setDetailAddress(orderCancleUserInfo.getDelivery().getDeliveryDetailAddress());
        orderUserInfo.setPhone(orderCancleUserInfo.getMember().getPhone());

        return orderUserInfo;
    }

}
