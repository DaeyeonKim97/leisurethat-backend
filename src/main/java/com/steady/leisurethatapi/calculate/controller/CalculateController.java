package com.steady.leisurethatapi.calculate.controller;

import com.steady.leisurethatapi.calculate.dto.*;
import com.steady.leisurethatapi.calculate.service.CalculateService;
import com.steady.leisurethatapi.common.dto.ResponseMessage;
import com.steady.leisurethatapi.database.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <pre>
 * Class : CalculateController
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
@RestController
@RequestMapping("/calculate")
public class CalculateController {

    private final CalculateService calculateService;

    @Autowired
    public CalculateController(CalculateService calculateService){
        this.calculateService = calculateService;
    }

    @GetMapping("")
    public ResponseEntity<?> getCalculateApplicationList(@RequestParam("projectId") int projectId, @RequestParam(name="offset", defaultValue = "0") int offset) {;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));
        Map<String,Object> responseMap = new HashMap<>();

//        Pageable pageable = PageRequest.of(offset, 6, Sort.by("id").descending());
//
//        List<CalculateApplicationStatusDTO> calculateApplicationList = calculateService.selectCalculateApplicationList(projectId, pageable);

        Sort sort = Sort.by(Sort.Direction.DESC, "id");

        List<CalculateApplicationStatusDTO> calculateApplicationList = calculateService.selectCalculateApplicationList(projectId, sort);
        CalculateAmountResultDTO calculateAmount = calculateService.selectCalculateAmount(projectId);
        System.out.println(calculateAmount);
        List<DeliveryStatusCount> deliveryStatusList = calculateService.selectDeliveryStatus(projectId);
        Project projectInfo = calculateService.selectProject(projectId);
        int excludingFees = 0;
        int preAmount = 0;

        if(calculateAmount != null) {
            if(calculateAmount.getActualAmount() != null) {
                excludingFees = (int) (calculateAmount.getActualAmount() * 0.95);
            }
            preAmount = (int)(excludingFees * 0.8);
            responseMap.put("totalAmount", calculateAmount.getTotal());
            responseMap.put("actualAmount", calculateAmount.getActualAmount());
        } else {
            responseMap.put("totalAmount", 0);
            responseMap.put("actualAmount",0);
        }

        responseMap.put("preAmount", preAmount);
        responseMap.put("postAmount", excludingFees - preAmount);
        responseMap.put("calculateList", calculateApplicationList);
        responseMap.put("projectName", projectInfo.getName());
        responseMap.put("makerName", projectInfo.getAccountInfo().getBusinessInfo().getMember().getName());

        CalculateApplicationResponseDTO calculateApplicationDetail = new CalculateApplicationResponseDTO();
        deliveryStatusList.forEach(deliveryStatusCount -> {
            if(deliveryStatusCount.getDeliveryStatus() == "배송중") {
                calculateApplicationDetail.setDeliveryOngoingCount(deliveryStatusCount.getDeliveryStatusCount());
            } else if(deliveryStatusCount.getDeliveryStatus() == "배송완료") {
                calculateApplicationDetail.setDeliveryCompleteCount(deliveryStatusCount.getDeliveryStatusCount());
            } else {
                calculateApplicationDetail.setDeliveryOnCallCount(deliveryStatusCount.getDeliveryStatusCount());
            }
        });
        responseMap.put("deliveryCompleteCount", calculateApplicationDetail.getDeliveryCompleteCount());
        responseMap.put("deliveryOngoingCount", calculateApplicationDetail.getDeliveryCompleteCount());
        responseMap.put("deliveryOnCallCount", calculateApplicationDetail.getDeliveryOnCallCount());
        responseMap.put("totalDeliveryCount", calculateApplicationDetail.getDeliveryCompleteCount() + calculateApplicationDetail.getDeliveryCompleteCount() + calculateApplicationDetail.getDeliveryOnCallCount());
        

        return ResponseEntity
                .ok()
                .headers(headers)
                .body(new ResponseMessage(200, "calculateList search success", responseMap));
    }

    @GetMapping("/{id}/reject")
    public ResponseEntity<?> getCalculateRejectReason(@PathVariable("id") int id) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));
        Map<String,Object> responseMap = new HashMap<>();

        int judgeId = calculateService.selectJudgeId(id).getJudge().getId();

        CalculateRejectResponseDTO rejectReason = calculateService.selectRejectInfo(judgeId);
        responseMap.put("rejectReason", rejectReason);

        return ResponseEntity
                .ok()
                .headers(headers)
                .body(new ResponseMessage(200, "reject reason search success", responseMap));
    }

    @GetMapping("/{id}/detail")
    public ResponseEntity<?> getCalculateApplicationDetail(@PathVariable("id") int id) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));
        Map<String,Object> responseMap = new HashMap<>();

        CalculateApplicationResponseDTO calculateApplication = calculateService.selectCalculateApplicationById(id);
        responseMap.put("calculateDetail",calculateApplication);

        return ResponseEntity
                .ok()
                .headers(headers)
                .body(new ResponseMessage(200, "calculateDetail search success", responseMap));
    }

    @GetMapping("/list")
    public ResponseEntity<?> getCaculateList(@RequestParam(value = "projectName", required = false) String projectName,
                                             @RequestParam(value = "makerId", required = false) Integer makerId,
                                             @RequestParam(value = "state", required = false) String state,
                                             @RequestParam(value = "offset", defaultValue="0") int offset) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));
        Map<String,Object> responseMap = new HashMap<>();

        Pageable pageable = PageRequest.of(offset, 6, Sort.by("id").descending());

        List<CalculateListReponseDTO> calculateList = calculateService.selectCalculateList(pageable);

        responseMap.put("calculateList", calculateList);

        return ResponseEntity
                .ok()
                .headers(headers)
                .body(new ResponseMessage(200, "calculate list search success", responseMap));
    }

    @GetMapping("/{id}/maker")
    public ResponseEntity<?> getMakerByCalculateId(@PathVariable("id") int id) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));
        Map<String,Object> responseMap = new HashMap<>();

        MakerInfoDTO makerInfo = calculateService.selectMakerInfo(id);

        responseMap.put("makerInfo", makerInfo);

        return ResponseEntity
                .ok()
                .headers(headers)
                .body(new ResponseMessage(200, "calculate application maker info search success", responseMap));
    }

    @GetMapping("/project/{id}")
    public ResponseEntity<?> getCaculateListByProjectId(@PathVariable("id") int id) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        Map<String, Object> responseMap = new HashMap<>();

        List<CalculateListReponseDTO> calculateList = calculateService.selectCalculateListByProjectId(id);

        responseMap.put("calculateList", calculateList);

        return ResponseEntity
                .ok()
                .headers(headers)
                .body(new ResponseMessage(200, "calculate list by project id search success", responseMap));
    }

    @GetMapping("{id}/project")
    public ResponseEntity<?> getProjectByCalculateId(@PathVariable("id") int id) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        Map<String, Object> responseMap = new HashMap<>();

        CalculateProjectInfoDTO projectInfo = calculateService.selectCalculateProjectInfoByCalculateId(id);
        responseMap.put("projectInfo", projectInfo);

        return ResponseEntity
                .ok()
                .headers(headers)
                .body(new ResponseMessage(200, "project search success", responseMap));
    }

}
