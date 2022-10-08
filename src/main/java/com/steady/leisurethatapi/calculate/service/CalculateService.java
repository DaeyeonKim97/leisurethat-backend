package com.steady.leisurethatapi.calculate.service;

import com.steady.leisurethatapi.calculate.dto.*;
import com.steady.leisurethatapi.database.entity.Calculate;
import com.steady.leisurethatapi.database.entity.OrderDelivery;
import com.steady.leisurethatapi.database.entity.Project;
import com.steady.leisurethatapi.database.entity.Reject;
import com.steady.leisurethatapi.database.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    private final ProjectRepository projectRepository;

    @Autowired
    public CalculateService(CalculateRepository calculateRepository,
                            PaymentRepository paymentRepository,
                            RejectRepository rejectRepository,
                            OrderDeliveryRepositroy orderDeliveryRepositroy,
                            ProjectRepository projectRepository) {
        this.calculateRepository = calculateRepository;
        this.paymentRepository = paymentRepository;
        this.rejectRepository = rejectRepository;
        this.orderDeliveryRepositroy = orderDeliveryRepositroy;
        this.projectRepository = projectRepository;
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
        CalculateAmountResultDTO calculateAmount = paymentRepository.findPaymentSum(calculateDetail.getJudge().getProject().getId());

        CalculateApplicationResponseDTO calculateApplicationDetail = new CalculateApplicationResponseDTO();
        calculateApplicationDetail.setProjectName(calculateDetail.getJudge().getProject().getName());
        calculateApplicationDetail.setProjectId(calculateDetail.getJudge().getProject().getId());
        calculateApplicationDetail.setCategory(calculateDetail.getJudge().getProject().getProjectCategory().getName());
        calculateApplicationDetail.setMakerUserName(calculateDetail.getJudge().getProject().getAccountInfo().getBusinessInfo().getMember().getUsername());
        calculateApplicationDetail.setCalculateRound(calculateDetail.getDivision().substring(0,2));
        calculateApplicationDetail.setCalculateStatus("정산 "+calculateDetail.getJudge().getJudgeDivision().getDes().substring(2,4));
        calculateApplicationDetail.setTotalAmount(calculateAmount.getActualAmount());

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

    public List<CalculateListReponseDTO> selectCalculateList(Pageable pageable) {

        Page<Calculate> calculates = calculateRepository.findAll(pageable);
        List<CalculateListReponseDTO> calculateList = new ArrayList<>();

        calculates.forEach(calculate -> {
            System.out.println(calculate);
            CalculateListReponseDTO calculateListReponseDTO = new CalculateListReponseDTO();
            calculateListReponseDTO.setCalculateId(calculate.getId());
            calculateListReponseDTO.setProjectId(calculate.getJudge().getProject().getId());
            calculateListReponseDTO.setMakerId(calculate.getJudge().getProject().getAccountInfo().getBusinessInfo().getMember().getId());
            calculateListReponseDTO.setMakerUsername(calculate.getJudge().getProject().getAccountInfo().getBusinessInfo().getMember().getUsername());
            calculateListReponseDTO.setMakerName(calculate.getJudge().getProject().getAccountInfo().getBusinessInfo().getMember().getName());
            calculateListReponseDTO.setProjectName(calculate.getJudge().getProject().getName());
            calculateListReponseDTO.setCalculateRound(calculate.getDivision().substring(0,2));
            calculateListReponseDTO.setCalculateRegDate(calculate.getJudge().getRegDate());

            if(calculate.getJudge().getJudgeDivision().getDes().substring(2,4).equals("승인") && calculate.getGiveDate() != null){
                calculateListReponseDTO.setCalculateStatus("정산 완료");
            } else {
                calculateListReponseDTO.setCalculateStatus("정산 " + calculate.getJudge().getJudgeDivision().getDes().substring(2,4));
            }

            calculateList.add(calculateListReponseDTO);
        });

        return calculateList;
    }

    public MakerInfoDTO selectMakerInfo(int id) {

        MakerInfoDTO makerInfo = new MakerInfoDTO();
        List<MakerProjectInfoDTO> projectList = new ArrayList<>();
        Calculate calculate = calculateRepository.findById(id);
        makerInfo.setName(calculate.getJudge().getProject().getAccountInfo().getBusinessInfo().getMember().getName());
        makerInfo.setPhone(calculate.getJudge().getProject().getAccountInfo().getBusinessInfo().getMember().getPhone());
        makerInfo.setBusinessId(calculate.getJudge().getProject().getAccountInfo().getBusinessInfo().getId());
        makerInfo.setBank(calculate.getJudge().getProject().getAccountInfo().getBank().getName());
        makerInfo.setAccountNumber(calculate.getJudge().getProject().getAccountInfo().getAccountNumber());

        List<Project> projectInfo = projectRepository.findAllByAccountInfoBusinessInfoMemberId(calculate.getJudge().getProject().getAccountInfo().getBusinessInfo().getMember().getId());
        makerInfo.setProjectCount(projectInfo.size());

        projectInfo.forEach(project -> {
            System.out.println(project);
            MakerProjectInfoDTO makerProjectInfo = new MakerProjectInfoDTO();
            int sumSupportAmount = paymentRepository.findSumSupportAmountByProjectId(project.getId());
            int achievement = (int)((double)sumSupportAmount / (double)project.getTargetAmount() * 100);

            makerProjectInfo.setProjectId(project.getId());
            makerProjectInfo.setProjectName(project.getName());
            makerProjectInfo.setAchievement(achievement);
            makerProjectInfo.setProjectEndDate(project.getEndDate());

            projectList.add(makerProjectInfo);
        });

        makerInfo.setMakerProject(projectList);

        return makerInfo;
    }

    public List<CalculateListReponseDTO> selectCalculateListByProjectId(int id) {

        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        List<Calculate> calculates = calculateRepository.findAllByJudgeProjectId(id, sort);
        List<CalculateListReponseDTO> calculateList = new ArrayList<>();

        calculates.forEach(calculate -> {
            CalculateListReponseDTO calculateListReponseDTO = new CalculateListReponseDTO();
            calculateListReponseDTO.setCalculateId(calculate.getId());
            calculateListReponseDTO.setProjectId(calculate.getJudge().getProject().getId());
            calculateListReponseDTO.setMakerId(calculate.getJudge().getProject().getAccountInfo().getBusinessInfo().getMember().getId());
            calculateListReponseDTO.setMakerUsername(calculate.getJudge().getProject().getAccountInfo().getBusinessInfo().getMember().getUsername());
            calculateListReponseDTO.setMakerName(calculate.getJudge().getProject().getAccountInfo().getBusinessInfo().getMember().getName());
            calculateListReponseDTO.setProjectName(calculate.getJudge().getProject().getName());
            calculateListReponseDTO.setCalculateRound(calculate.getDivision().substring(0,2));
            calculateListReponseDTO.setCalculateRegDate(calculate.getJudge().getRegDate());

            if(calculate.getJudge().getJudgeDivision().getDes().substring(2,4).equals("승인") && calculate.getGiveDate() != null){
                calculateListReponseDTO.setCalculateStatus("정산 완료");
            } else {
                calculateListReponseDTO.setCalculateStatus("정산 " + calculate.getJudge().getJudgeDivision().getDes().substring(2,4));
            }

            calculateList.add(calculateListReponseDTO);
        });


        return calculateList;
    }

    public CalculateProjectInfoDTO selectCalculateProjectInfoByCalculateId(int id) {

        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Calculate calculate = calculateRepository.findById(id);
        CalculateAmountResultDTO calculateAmount = paymentRepository.findPaymentSum(calculate.getJudge().getProject().getId());
        int sumSupportAmount = paymentRepository.findSumSupportAmountByProjectId(calculate.getJudge().getProject().getId());
        int achievement = (int)((double)sumSupportAmount / (double)calculate.getJudge().getProject().getTargetAmount() * 100);
        int supportCount = paymentRepository.findSupportCount(calculate.getJudge().getProject().getId());

        CalculateProjectInfoDTO projectInfo = new CalculateProjectInfoDTO();
        projectInfo.setMakerName(calculate.getJudge().getProject().getAccountInfo().getBusinessInfo().getMember().getName());
        projectInfo.setMakerEmail(calculate.getJudge().getProject().getAccountInfo().getBusinessInfo().getMember().getEmail());
        projectInfo.setMakerPhone(calculate.getJudge().getProject().getAccountInfo().getBusinessInfo().getMember().getPhone());
        projectInfo.setProjectName(calculate.getJudge().getProject().getName());
        projectInfo.setProjectImg(calculate.getJudge().getProject().getAttachment().getDownloadAddress());
        projectInfo.setProjectCategory(calculate.getJudge().getProject().getProjectCategory().getName());
        projectInfo.setProjectStartDate(calculate.getJudge().getProject().getStartDate());
        projectInfo.setProjectEndDate(calculate.getJudge().getProject().getEndDate());
        projectInfo.setActualAmount(calculateAmount.getActualAmount());
        projectInfo.setTotalAmount(calculateAmount.getTotal());
        projectInfo.setPreAmount(calculate.getPreAmount());
        projectInfo.setAchievement(achievement);
        projectInfo.setSupportCount(supportCount);

        if(calculate.getPostAmount() != null) {
            projectInfo.setPostAmount(calculate.getPostAmount());
            projectInfo.setPostAmountGiveDate(calculate.getGiveDate());
            List<Calculate> calculateList = calculateRepository.findAllByJudgeProjectId(calculate.getJudge().getProject().getId(), sort);

            calculateList.forEach(cal -> {
                if(cal.getPostAmount() == null && cal.getGiveDate() != null) {
                    projectInfo.setPreAmountGiveDate(cal.getGiveDate());
                }
            });

        } else {
            projectInfo.setPreAmountGiveDate(calculate.getGiveDate());
        }


        return projectInfo;
    }
}
