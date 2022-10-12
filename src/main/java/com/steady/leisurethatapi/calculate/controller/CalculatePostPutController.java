package com.steady.leisurethatapi.calculate.controller;

import com.steady.leisurethatapi.attachment.service.UploadS3Service;
import com.steady.leisurethatapi.calculate.vo.CalculateApplicationVO;
import com.steady.leisurethatapi.calculate.vo.CalculateJudgeVO;
import com.steady.leisurethatapi.calculate.vo.CalculateRejectVO;
import com.steady.leisurethatapi.common.dto.ResponseMessage;
import com.steady.leisurethatapi.database.entity.*;
import com.steady.leisurethatapi.database.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.nio.charset.Charset;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * <pre>
 * Class : CalculatePostPutController
 * Comment: 클래스에 대한 간단 설명
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-10-10       홍길동           최초 생성
 * </pre>
 *
 * @author 홍길동(최초 작성자)
 * @version 1(클래스 버전)
 * @see
 */
@RestController
@RequestMapping("/calculate")
public class CalculatePostPutController {

    private final CalculateRepository calculateRepository;
    private final ProjectRepository projectRepository;
    private final JudgeRepository judgeRepository;
    private final ProjectStatusRepository projectStatusRepository;
    private final RejectRepository rejectRepository;
    private final JudgeDivisionRepository judgeDivisionRepository;
    private final UploadS3Service uploadS3Service;

    @Autowired
    public CalculatePostPutController(CalculateRepository calculateRepository,
                                      ProjectRepository projectRepository,
                                      JudgeRepository judgeRepository,
                                      ProjectStatusRepository projectStatusRepository,
                                      RejectRepository rejectRepository,
                                      JudgeDivisionRepository judgeDivisionRepository,
                                      UploadS3Service uploadS3Service) {

        this.calculateRepository = calculateRepository;
        this.projectRepository = projectRepository;
        this.judgeRepository = judgeRepository;
        this.projectStatusRepository = projectStatusRepository;
        this.rejectRepository = rejectRepository;
        this.judgeDivisionRepository = judgeDivisionRepository;
        this.uploadS3Service = uploadS3Service;
    }


    @Transactional
    @PostMapping("/application")
    public ResponseEntity<?> postCalculateApplication(@AuthenticationPrincipal UserDetails userDetails, CalculateApplicationVO vo) {

        System.out.println(vo);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        Map<String, Object> responseMap = new HashMap<>();

        System.out.println(vo);

        int projectStatusCode = 0;
        int judgeDivisionId = 0;

        switch (vo.getCalculateStatus()) {
            case "1차정산요청" :
                projectStatusCode = 14;
                judgeDivisionId = 1;
                break;

            case "2차정산요청" :
                projectStatusCode = 16;
                judgeDivisionId = 4;
                break;

        }

        System.out.println(projectStatusCode);
        Project project = projectRepository.findById(vo.getProjectId());
        ProjectStatus projectStatus = projectStatusRepository.findById(projectStatusCode);
        project.setStatus(projectStatus);
        Judge judge = null;
        try{
            Project modifyProject = projectRepository.save(project);
            System.out.println(modifyProject);
            if(vo.getPreCalApplicationFile() != null) {
                Attachment atc = uploadS3Service.uploadFile("calculate", vo.getPreCalApplicationFile());
//                int id =1;
//                Attachment atc = new Attachment();
//                atc.setId(id);
                judge = judgeRepository.save(new Judge(
                        0,
                        vo.getTitle(),
                        vo.getContent(),
                        new Date(new java.util.Date().getTime()),
                        new JudgeDivision(judgeDivisionId,null),
                        modifyProject,
                        null,
                        atc
                ));
            } else {
                judge = judgeRepository.save(new Judge(
                        0,
                        vo.getTitle(),
                        vo.getContent(),
                        new Date(new java.util.Date().getTime()),
                        new JudgeDivision(judgeDivisionId,null),
                        modifyProject,
                        null,
                        null
                ));
            }

            Calculate calculate = calculateRepository.save(new Calculate(
                    0,
                    vo.getCalculateDivision(),
                    vo.getTotalCalAmount(),
                    vo.getPreCalAmount(),
                    vo.getBalance(),
                    vo.getPostCalAmount(),
                    null,
                    judge
            ));

            responseMap.put("insertCalculate", calculate);
        } catch (Exception e){

            responseMap.put("failMessage", "정산 신청에 실패하셨습니다.");

            return ResponseEntity
                    .ok()
                    .headers(headers)
                    .body(new ResponseMessage(400, "fail calculate applicaion", responseMap));

        }

        return ResponseEntity
                .created(URI.create("/calculate?projectId=" + vo.getProjectId()))
                .headers(headers)
                .body(new ResponseMessage(201, "calculate application success", responseMap));
    }

    @Transactional
    @PostMapping("/judge")
    public ResponseEntity<?> postCalculateJudge(@AuthenticationPrincipal UserDetails userDetails, @RequestBody CalculateJudgeVO vo) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        Map<String, Object> responseMap = new HashMap<>();

        System.out.println(vo);
        int judgeDivisionId = 0;
        int projectStatusId = 0;

        Judge modifyJudge = null;
        Calculate modifyCalculate = null;
        Calculate calculate = null;
        Reject reject = null;

        if(vo.getCalculateRound().equals("1차")) {
            if(vo.getCalculateJudgeStatus().equals("승인")) {
                judgeDivisionId = 3;
                projectStatusId = 15;
            } else {
                judgeDivisionId = 2;
                projectStatusId = 14;
            }
        }

        if(vo.getCalculateRound().equals("2차")) {
            if(vo.getCalculateJudgeStatus().equals("승인")) {
                judgeDivisionId = 6;
                projectStatusId = 17;
            } else {
                judgeDivisionId = 5;
                projectStatusId = 16;
            }
        }

        try{
            Project project = projectRepository.findById(vo.getProjectId());
            ProjectStatus projectStatus = projectStatusRepository.findById(projectStatusId);
            System.out.println(projectStatus);
            project.setStatus(projectStatus);
            Project modifyProject = projectRepository.save(project);
            calculate = calculateRepository.findById(vo.getCalculateId());
            Optional<JudgeDivision> judgeDivision = judgeDivisionRepository.findById(judgeDivisionId);
            Judge judge = calculate.getJudge();
            judgeDivision.ifPresent(division -> {
                judge.setJudgeDivision(division);
            });
            modifyJudge = judgeRepository.save(judge);

            if(vo.getCalculateJudgeStatus().equals("승인")) {
                calculate.setGiveDate(new Date(new java.util.Date().getTime()));
                calculate.setJudge(modifyJudge);
                modifyCalculate = calculateRepository.save(calculate);
            }

            if(vo.getCalculateJudgeStatus().equals("반려")) {
                reject = rejectRepository.save(new Reject(
                        0,
                        vo.getCalculateRejectTitle(),
                        vo.getCalculateRejectContent(),
                        new Date(new java.util.Date().getTime()),
                        modifyJudge
                ));

                calculate.setJudge(modifyJudge);
            }




        } catch(Exception e) {
            responseMap.put("failMessage", "정산 심사에 실패하셨습니다.");

            return ResponseEntity
                    .ok()
                    .headers(headers)
                    .body(new ResponseMessage(400, "fail calculate judge", responseMap));


        }

        if(reject != null) {
            responseMap.put("reject", reject);
            responseMap.put("calculate", calculate);
        } else {
            responseMap.put("calculate", modifyCalculate);
        }

        return ResponseEntity
                .created(URI.create("/calculate/judge"))
                .headers(headers)
                .body(new ResponseMessage(201, "calcualte judge success", responseMap));
    }

    @Transactional
    @PutMapping("/judge/reject")
    public ResponseEntity<?> putCaculateReject(@RequestBody CalculateRejectVO vo){

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        Map<String, Object> responseMap = new HashMap<>();

        Reject modifyReject = null;

        try {
            Reject reject = rejectRepository.findByJudgeId(vo.getJudgeId());
            reject.setContent(vo.getRejectContent());
            modifyReject = rejectRepository.save(reject);

        } catch(Exception e) {
            responseMap.put("failMessage", "반려 사유 수정에 실패하셨습니다.");

            return ResponseEntity
                    .ok()
                    .headers(headers)
                    .body(new ResponseMessage(400, "fail reject modify", responseMap));


        }

        responseMap.put("modifyReject", modifyReject);


        return ResponseEntity
                .ok()
                .headers(headers)
                .body(new ResponseMessage(200, "calcualte judge success", responseMap));
    }
}
