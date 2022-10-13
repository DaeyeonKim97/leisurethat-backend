package com.steady.leisurethatapi.user.service;

import com.steady.leisurethatapi.database.entity.*;
import com.steady.leisurethatapi.database.repository.*;
import com.steady.leisurethatapi.user.dto.FundingResponseDTO;
import com.steady.leisurethatapi.user.dto.MakeProjectResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * Class : Userservice
 * Comment: 클래스에 대한 간단 설명
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-10-08       전현정           최초 생성
 * </pre>
 *
 * @author 전현정(최초 작성자)
 * @version 1(클래스 버전)
 * @see
 */
@Service
public class UserService {

    private final PaymentRepository paymentRepository;
    private final OrderDeliveryRepositroy orderDeliveryRepositroy;
    private final ProjectRepository projectRepository;
    private final OrderDetailRepository orderDetailRepository;


    @Autowired
    public UserService(PaymentRepository paymentRepository, OrderDeliveryRepositroy orderDeliveryRepositroy, ProjectRepository projectRepository, OrderDetailRepository orderDetailRepository) {
        this.paymentRepository = paymentRepository;
        this.orderDeliveryRepositroy = orderDeliveryRepositroy;
        this.projectRepository = projectRepository;
        this.orderDetailRepository = orderDetailRepository;
    }

    public FundingResponseDTO selectFundingList(String username, Pageable pageable) {

        FundingResponseDTO fundingResponse = new FundingResponseDTO();

        List<Payment> payments = paymentRepository.findAllByOrderMemberUsername(username, pageable);

        payments.forEach(payment -> {
            System.out.println(payment);
            OrderDelivery orderDelivery = orderDeliveryRepositroy.findByOrderId(payment.getOrder().getId());
            OrderDetail orderDetail = orderDetailRepository.findByOrderId(payment.getOrder().getId());
            Integer sumSupportAmount = paymentRepository.findSumSupportAmountByProjectId(payment.getOrder().getProject().getId());

            int achievement = 0;
            if(sumSupportAmount != null) {
                achievement = (int)((double)sumSupportAmount / (double) payment.getOrder().getProject().getTargetAmount() * 100);
            }

            if(orderDelivery != null && orderDetail != null) {

                fundingResponse.setProjectImg(payment.getOrder().getProject().getAccountInfo().getAttachment().getDownloadAddress());
                fundingResponse.setPaymentDivision("토스페이먼트 카드");

                fundingResponse.setOrderId(payment.getOrder().getId());
                fundingResponse.setOrderDate(payment.getOrder().getOrderDate());
                fundingResponse.setProjectName(payment.getOrder().getProject().getName());
                fundingResponse.setProjectEndDate(payment.getOrder().getProject().getEndDate());
                fundingResponse.setPaymentInfo(payment.getPaymentDivision());
                fundingResponse.setPaymentStatus(payment.getPaymentStatus());
                fundingResponse.setPaymentPrice(payment.getPaymentPrice());
                fundingResponse.setBasicAddress(orderDelivery.getDelivery().getDeliveryBasicAddress());
                fundingResponse.setDetailAddress(orderDelivery.getDelivery().getDeliveryDetailAddress());
                fundingResponse.setReceiver(orderDelivery.getDelivery().getDeliveryReceiver());
                fundingResponse.setReceiverPhone(orderDelivery.getDelivery().getContact());
                fundingResponse.setRewardName(payment.getOrder().getReward().getTitle());
                fundingResponse.setRewardAmount(orderDetail.getRewardAmount());
                fundingResponse.setAchievement(achievement);
            }

        });

        return fundingResponse;
    }
    public int selectFundingCount(String username) {
        List<Payment> payments = paymentRepository.findAllByOrderMemberUsername(username);

        if(payments != null) {
            return payments.size();
        } else {
            return 0;
        }
    }

    public List<MakeProjectResponseDTO> selectMakerProjectList(String username, Pageable pageable) {

        List<MakeProjectResponseDTO> makeProjectList = new ArrayList<>();

        List<Project> projects = projectRepository.findAllByAccountInfoBusinessInfoMemberUsername(username, pageable);

        projects.forEach(project -> {
            MakeProjectResponseDTO makeProjectInfo = new MakeProjectResponseDTO();

            System.out.println(project.getId());
            Integer sumSupportAmount = paymentRepository.findSumSupportAmountByProjectId(project.getId());
            int achievement = 0;
            if(sumSupportAmount != null) {
                achievement = (int)((double)sumSupportAmount / (double) project.getTargetAmount() * 100);
            }


            makeProjectInfo.setProjectId(project.getId());
            makeProjectInfo.setProjectName(project.getName());
            makeProjectInfo.setProjectCategory(project.getProjectCategory().getName());
            makeProjectInfo.setTargetAmount(project.getTargetAmount());
            makeProjectInfo.setProjectStatus(project.getStatus().getDescription());
            makeProjectInfo.setAchievement(achievement);
            makeProjectInfo.setMakerName(project.getAccountInfo().getBusinessInfo().getMember().getName());

            makeProjectList.add(makeProjectInfo);
        });


        return makeProjectList;
    }

    public int selectProjectCount(String username) {
        List<Project> projects = projectRepository.findAllByAccountInfoBusinessInfoMemberUsername(username);

        if(projects != null) {
            return projects.size();
        } else {
            return 0;
        }
    }
}
