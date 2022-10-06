package com.steady.leisurethatapi.calculate.service;

import com.steady.leisurethatapi.calculate.dto.CalculateAmountResultDTO;
import com.steady.leisurethatapi.calculate.dto.CalculateApplicationStatusDTO;
import com.steady.leisurethatapi.database.entity.Calculate;
import com.steady.leisurethatapi.database.repository.CalculateRepository;
import com.steady.leisurethatapi.database.repository.PaymentRepository;
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

    @Autowired
    public CalculateService(CalculateRepository calculateRepository, PaymentRepository paymentRepository) {
        this.calculateRepository = calculateRepository;
        this.paymentRepository = paymentRepository;
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
}
