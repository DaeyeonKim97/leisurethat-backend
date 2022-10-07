package com.steady.leisurethatapi.calculate.service;

import com.steady.leisurethatapi.calculate.dto.*;
import com.steady.leisurethatapi.database.entity.Calculate;
import com.steady.leisurethatapi.database.entity.OrderDelivery;
import com.steady.leisurethatapi.database.entity.Reject;
import com.steady.leisurethatapi.database.repository.CalculateRepository;
import com.steady.leisurethatapi.database.repository.OrderDeliveryRepositroy;
import com.steady.leisurethatapi.database.repository.PaymentRepository;
import com.steady.leisurethatapi.database.repository.RejectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * Class : CalculateService
 * Comment: 클래스에 대한 간단 설명
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-10-06       전현정           최초 생성
 * </pre>
 *
 * @author 전현정(최초 작성자)
 * @version 1(클래스 버전)
 * @see
 */
@Service
public class CalculateService {

    private final CalculateRepository calculateRepository;
    private final PaymentRepository paymentRepository;
    private final RejectRepository rejectRepository;
    private final OrderDeliveryRepositroy orderDeliveryRepositroy;

    @Autowired
    public CalculateService(CalculateRepository calculateRepository,
                            PaymentRepository paymentRepository,
                            RejectRepository rejectRepository,
                            OrderDeliveryRepositroy orderDeliveryRepositroy) {
        this.calculateRepository = calculateRepository;
        this.paymentRepository = paymentRepository;
        this.rejectRepository = rejectRepository;
        this.orderDeliveryRepositroy = orderDeliveryRepositroy;
    }


    public List<CalculateApplicationStatusDTO> selectCalculateApplicationList(int projectId, Pageable pageable) {

        List<Calculate> calculates = calculateRepository.findAllByJudgeProjectId(projectId, pageable);

        List<CalculateApplicationStatusDTO> calculateList = new ArrayList<>();

        calculates.forEach(calculate -> {
            CalculateApplicationStatusDTO calculateApplicationStatusDTO = new CalculateApplicationStatusDTO();
            calculateApplicationStatusDTO.setCalculateId(calculate.getId());
            calculateApplicationStatusDTO.setCalculateRound(calculate.getDivision().substring(0,2));
            calculateApplicationStatusDTO.setStatus("정산"+calculate.getJudge().getJudgeDivision().getDes().substring(2,4));

            if(calculate.getPostAmount() != null) {
                calculateApplicationStatusDTO.setAmount(calculate.getPostAmount());
            } else {
                calculateApplicationStatusDTO.setAmount(calculate.getPreAmount());
            }

            calculateApplicationStatusDTO.setRegDate(calculate.getJudge().getRegDate());
            calculateApplicationStatusDTO.setGiveDate(calculate.getGiveDate());
            calculateList.add(calculateApplicationStatusDTO);
        });

        return calculateList;
    }

    public CalculateAmountResultDTO selectCalculateAmount(int projectId) {

        return paymentRepository.findPaymentSum(projectId);
    }

    public Calculate selectJudgeId(int id) {

        return calculateRepository.findById(id);
    }

    public CalculateRejectResponseDTO selectRejectInfo(int judgeId) {

        Reject reject = rejectRepository.findByJudgeId(judgeId);

        CalculateRejectResponseDTO rejectReason = new CalculateRejectResponseDTO();

        rejectReason.setProjectName(reject.getJudge().getProject().getName());
        rejectReason.setCategory(reject.getJudge().getProject().getProjectCategory().getName());
        rejectReason.setAtcDownload(reject.getJudge().getAtc().getDownloadAddress());
        rejectReason.setMakerUserName(reject.getJudge().getProject().getAccountInfo().getBusinessInfo().getMember().getUsername());
        rejectReason.setRejectTitle(reject.getTitle());
        rejectReason.setRejectContent(reject.getContent());

        System.out.println(rejectReason);

        return rejectReason;
    }

    public CalculateApplicationResponseDTO selectCalculateApplicationById(int id) {

        Calculate calculateDetail = calculateRepository.findById(id);

        CalculateApplicationResponseDTO calculateApplicationDetail = new CalculateApplicationResponseDTO();
        calculateApplicationDetail.setProjectName(calculateDetail.getJudge().getProject().getName());
        calculateApplicationDetail.setCategory(calculateDetail.getJudge().getProject().getProjectCategory().getName());
        calculateApplicationDetail.setMakerUserName(calculateDetail.getJudge().getProject().getAccountInfo().getBusinessInfo().getMember().getUsername());
        calculateApplicationDetail.setCalculateRound(calculateDetail.getDivision().substring(0,2));
        calculateApplicationDetail.setCalculateStatus("정산 "+calculateDetail.getJudge().getJudgeDivision().getDes().substring(2,4));

        if(calculateDetail.getPostAmount() != null) {
            calculateApplicationDetail.setCalculateAmount(calculateDetail.getPostAmount());
            List<DeliveryStatusCount> deliveryStatusList = orderDeliveryRepositroy.findByProjectId(calculateDetail.getJudge().getProject().getId());
            deliveryStatusList.forEach(deliveryStatusCount -> {
                if(deliveryStatusCount.getDeliveryStatus() == "배송중") {
                    calculateApplicationDetail.setDeliveryOngoingCount(deliveryStatusCount.getDeliveryStatusCount());
                } else if(deliveryStatusCount.getDeliveryStatus() == "배송완료") {
                    calculateApplicationDetail.setDeliveryCompleteCount(deliveryStatusCount.getDeliveryStatusCount());
                } else {
                    calculateApplicationDetail.setDeliveryOnCallCount(deliveryStatusCount.getDeliveryStatusCount());
                }
            });

            calculateApplicationDetail.setTotalDeliveryCount(
                    calculateApplicationDetail.getDeliveryCompleteCount() +
                    calculateApplicationDetail.getDeliveryOnCallCount() +
                    calculateApplicationDetail.getDeliveryOngoingCount()
            );
        } else {
            calculateApplicationDetail.setCalculateAmount(calculateDetail.getPreAmount());
            calculateApplicationDetail.setAtcDownload(calculateDetail.getJudge().getAtc().getDownloadAddress());
        }

        return calculateApplicationDetail;
    }
}
