package com.steady.leisurethatapi.order.service;

import com.steady.leisurethatapi.database.entity.OrderDelivery;
import com.steady.leisurethatapi.database.entity.Payment;
import com.steady.leisurethatapi.database.repository.OrderDeliveryRepositroy;
import com.steady.leisurethatapi.database.repository.PaymentRepository;
import com.steady.leisurethatapi.order.dto.OrderCompleteDTO;
import com.steady.leisurethatapi.order.dto.OrderInfoDTO;
import com.steady.leisurethatapi.order.dto.OrderUserInfoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
    public List<OrderInfoDTO> selectOrderCancleList(int projectId, int id, String sponserName, String orderStatus, Pageable pageable) {

        List<Payment> paymentList = null;
//        paymentList = paymentList = paymentRepository.findAllByOrderProjectIdAndOrderOrderStatus(projectId, orderStatus, pageable);
        if(id > 0 && sponserName != null){
            paymentList = paymentRepository.findAllByOrderProjectIdAndOrderStatusAndOrderIdAndOrderMemberName(projectId, orderStatus, id, sponserName, pageable);
        } else if (id > 0) {
            paymentList = paymentRepository.findAllByOrderProjectIdAndOrderStatusAndOrderId(projectId, orderStatus, id, pageable);
        } else if(sponserName != null) {
            paymentList = paymentRepository.findAllByOrderProjectIdAndOrderStatusAndOrderMemberName(projectId, orderStatus, sponserName, pageable);
        } else {
            paymentList = paymentRepository.findAllByOrderProjectIdAndOrderStatus(projectId, orderStatus, pageable);
        }

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
    public int selectOrderCancleListCount(int projectId, int id, String sponserName, String orderStatus) {
        int count = 0;
        if(id > 0 && sponserName != null){
            count = paymentRepository.findCancleCountByOrderProjectIdAndOrderStatusAndOrderIdAndOrderMemberName(projectId, orderStatus, id, sponserName);
        } else if (id > 0) {
            count = paymentRepository.findCancleCountByOrderProjectIdAndOrderStatusAndOrderId(projectId, orderStatus, id);
        } else if(sponserName != null) {
            count = paymentRepository.findCancleCountByOrderProjectIdAndOrderStatusAndOrderMemberName(projectId, orderStatus, sponserName);
        } else {
            count = paymentRepository.findCancleCountByOrderProjectIdAndOrderStatus(projectId, orderStatus);
        }

        return count;
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

    public List<OrderInfoDTO> selectOrderWaitingList(int projectId, int id, String sponserName, String orderStatus, Pageable pageable) {

        List<Payment> paymentList = null;
//        paymentList = paymentList = paymentRepository.findAllByOrderProjectIdAndOrderOrderStatus(projectId, orderStatus, pageable);
        if(id > 0 && sponserName != null){
            paymentList = paymentRepository.findAllByOrderProjectIdAndOrderStatusAndOrderIdAndOrderMemberName(projectId, orderStatus, id, sponserName, pageable);
        } else if (id > 0) {
            paymentList = paymentRepository.findAllByOrderProjectIdAndOrderStatusAndOrderId(projectId, orderStatus, id, pageable);
        } else if(sponserName != null) {
            paymentList = paymentRepository.findAllByOrderProjectIdAndOrderStatusAndOrderMemberName(projectId, orderStatus, sponserName, pageable);
        } else {
            paymentList = paymentRepository.findAllByOrderProjectIdAndOrderStatus(projectId, orderStatus, pageable);
        }

        List<OrderInfoDTO> waitingList = new ArrayList<>();

        //람다식
        paymentList.forEach(payment -> {
            OrderInfoDTO orderInfo = new OrderInfoDTO();

            orderInfo.setOrderId(payment.getOrder().getId());
            orderInfo.setPaymentPrice(payment.getPaymentPrice());
            orderInfo.setRewardName(payment.getOrder().getReward().getTitle());
            orderInfo.setSponserName(payment.getOrder().getMember().getName());
            orderInfo.setOrderStatus(payment.getOrder().getStatus());

            waitingList.add(orderInfo);
        });


        return waitingList;


    }

    public List<OrderCompleteDTO> selectOrderCompleteList(int projectId, int id, String sponserName, String orderStatus, Pageable pageable) {

        List<Payment> paymentList = null;
//        paymentList = paymentList = paymentRepository.findAllByOrderProjectIdAndOrderOrderStatus(projectId, orderStatus, pageable);
        if(id > 0 && sponserName != null){
            paymentList = paymentRepository.findAllByOrderProjectIdAndOrderStatusAndOrderIdAndOrderMemberName(projectId, orderStatus, id, sponserName, pageable);
        } else if (id > 0) {
            paymentList = paymentRepository.findAllByOrderProjectIdAndOrderStatusAndOrderId(projectId, orderStatus, id, pageable);
        } else if(sponserName != null) {
            paymentList = paymentRepository.findAllByOrderProjectIdAndOrderStatusAndOrderMemberName(projectId, orderStatus, sponserName, pageable);
        } else {
            paymentList = paymentRepository.findAllByOrderProjectIdAndOrderStatus(projectId, orderStatus, pageable);
        }

        List<OrderCompleteDTO> completeList = new ArrayList<>();

        //람다식
        paymentList.forEach(payment -> {
            OrderCompleteDTO orderComplete = new OrderCompleteDTO();

//            System.out.println(payment.getOrder().getId());

            orderComplete.setOrderId(payment.getOrder().getId());
            orderComplete.setPaymentPrice(payment.getPaymentPrice());
            orderComplete.setRewardName(payment.getOrder().getReward().getTitle());
            orderComplete.setSponserName(payment.getOrder().getMember().getName());
            orderComplete.setOrderStatus(payment.getOrder().getStatus());
            orderComplete.setDeliveryDate(orderDelivery.getDeliveryDate());
            orderComplete.setDeliveryStatus(orderDelivery.getDelivertStatus());

            OrderDelivery orderDelivery = orderDeliveryRepositroy.findByOrderId(payment.getOrder().getId());

            if(orderDelivery != null) {
                System.out.println(orderDelivery);
                orderComplete.setDeliveryDate(orderDelivery.getDeliveryDate());
                orderComplete.setDeliveryStatus(orderDelivery.getDeliveryStatus());
            }

            completeList.add(orderComplete);
        });

        return completeList;


    }


    @Transactional
    public void postWaybill(OrderCompleteDTO newWaybill) {

        OrderDelivery orderDelivery = orderDeliveryRepositroy.findByOrderId(newWaybill.getOrderId());
        System.out.println(orderDelivery);
        orderDelivery.setWaybillId(newWaybill.getWaybillId());
        orderDelivery.setCourierId(newWaybill.getCourierId());
    }


}
