package com.steady.leisurethatapi.project.manage.controller;

import com.steady.leisurethatapi.common.dto.ResponseMessage;
import com.steady.leisurethatapi.database.entity.Member;
import com.steady.leisurethatapi.database.entity.Project;
import com.steady.leisurethatapi.database.repository.*;
import com.steady.leisurethatapi.project.manage.dto.MakerDetailResponseDTO;
import com.steady.leisurethatapi.project.manage.dto.PaymentResponseDTO;
import com.steady.leisurethatapi.project.manage.dto.ProjectDetailResponseDTO;
import com.steady.leisurethatapi.project.manage.dto.ProjectListResponseDTO;
import com.steady.leisurethatapi.project.manage.service.ProjectDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


import java.net.URI;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("project-detail")
public class ProjectDetailController {

    private final ProjectRepository projectRepository;
    private final MemberRepository memberRepository;
    private final ProjectDetailService projectDetailService;
    @Autowired
    public ProjectDetailController(ProjectRepository projectRepository, MemberRepository memberRepository, ProjectDetailService projectDetailService) {
        this.projectRepository = projectRepository;
        this.memberRepository = memberRepository;
        this.projectDetailService = projectDetailService;
    }


    @GetMapping("{projectId}")
    public ResponseEntity<?> getProjectDetailInfo(@PathVariable int projectId){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));
        Map<String , Object> responseMap = new HashMap<>();
        String username = null;
        try{
            username = SecurityContextHolder.getContext().getAuthentication().getName();
        } catch (Exception e){
            System.out.println(e);
            return ResponseEntity
                    .badRequest()
                    .build();
        }
        Project project = projectRepository.findById(projectId);
        Member member = memberRepository.findByUsername(username);
        if(project == null){
            return ResponseEntity
                    .badRequest()
                    .build();
        }
        if((!(member.getRole().equals("ADMIN"))) && !(project.getBusinessInfo().getMember().getUsername().equals(username))){
            return ResponseEntity
                    .status(401)
                    .build();
        }

        ProjectDetailResponseDTO projectDetailResponse = projectDetailService.getProjectDetail(projectId);
        responseMap.put("project", projectDetailResponse);

        return ResponseEntity
                .ok()
                .body(new ResponseMessage(200,"success",responseMap));
    }

    @GetMapping("{projectId}/maker")
    public ResponseEntity<?> getMakerDetailInfo(@PathVariable int projectId){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));
        Map<String , Object> responseMap = new HashMap<>();

        MakerDetailResponseDTO maker = projectDetailService.getMakerDetail(projectId);
        responseMap.put("maker",maker);

        return ResponseEntity
                .ok()
                .body(new ResponseMessage(200,"success",responseMap));
    }

    @GetMapping("{projectId}/participant")
    public ResponseEntity<?> getParticipantDetailInfo(@PathVariable int projectId){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));
        Map<String , Object> responseMap = new HashMap<>();

        List<PaymentResponseDTO> paymentList = projectDetailService.getParticipantDetailInfo(projectId);
        responseMap.put("paymentList",paymentList);

        return ResponseEntity
                .ok()
                .body(new ResponseMessage(200,"success",responseMap));
    }

    @GetMapping("enroll")
    public ResponseEntity<?> getEnrollList(Pageable pageable){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));
        Map<String , Object> responseMap = new HashMap<>();

        List<ProjectListResponseDTO> projectList = projectDetailService.getEnrollList(pageable);
        responseMap.put("projectList", projectList);
        int total = projectRepository.countByStatusId(1);
        responseMap.put("total",total);

        return ResponseEntity
                .ok()
                .body(new ResponseMessage(200,"success",responseMap));
    }

    @PutMapping("enroll/{projectId}")
    public ResponseEntity<?> admitEnroll(@PathVariable int projectId){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));
        Map<String , Object> responseMap = new HashMap<>();

        int result = projectDetailService.admitEnroll(projectId);

        if (result < 0){
            return ResponseEntity
                    .badRequest()
                    .build();
        }

        return ResponseEntity
                .created(URI.create("project"))
                .build();
    }

    @DeleteMapping("enroll/{projectId}")
    public ResponseEntity<?> refuseEnroll(@PathVariable int projectId){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));
        Map<String , Object> responseMap = new HashMap<>();

        int result = projectDetailService.refuseEnroll(projectId);

        if (result < 0){
            return ResponseEntity
                    .badRequest()
                    .build();
        }

        return ResponseEntity
                .noContent()
                .build();
    }

    @GetMapping("preopen")
    public ResponseEntity<?> getPreOpenList(Pageable pageable){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));
        Map<String , Object> responseMap = new HashMap<>();

        List<ProjectListResponseDTO> projectList = projectDetailService.getPreOpenList(pageable);
        responseMap.put("projectList", projectList);

        int total = projectRepository.countByStatusId(3);
        responseMap.put("total",total);

        return ResponseEntity
                .ok()
                .body(new ResponseMessage(200,"success",responseMap));
    }

    @GetMapping("open")
    public ResponseEntity<?> getOpenList(Pageable pageable){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));
        Map<String , Object> responseMap = new HashMap<>();

        List<ProjectListResponseDTO> projectList = projectDetailService.getOpenList(pageable);
        responseMap.put("projectList", projectList);

        int total = projectRepository.countByStatusId(5);
        responseMap.put("total",total);

        return ResponseEntity
                .ok()
                .body(new ResponseMessage(200,"success",responseMap));
    }

    @DeleteMapping("open/{projectId}")
    public ResponseEntity<?> rejectOpenProject(@PathVariable int projectId){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));

        int result = projectDetailService.rejectOpenProject(projectId);

        if (result < 0){
            return ResponseEntity
                    .badRequest()
                    .build();
        }

        return ResponseEntity
                .noContent()
                .build();
    }

    @GetMapping("reg-forgive")
    public ResponseEntity<?> regForgiveList(Pageable pageable){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));
        Map<String , Object> responseMap = new HashMap<>();

        List<ProjectListResponseDTO> projectList = projectDetailService.getRegForgiveList(pageable);
        responseMap.put("projectList", projectList);

        int total = projectRepository.countByStatusId(6);
        responseMap.put("total",total);

        return ResponseEntity
                .ok()
                .body(new ResponseMessage(200,"success",responseMap));
    }

    @DeleteMapping("reg-forgive/{projectId}")
    public ResponseEntity<?> admitForgive(@PathVariable int projectId){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));

        int result = projectDetailService.admitForgive(projectId);

        if (result < 0){
            return ResponseEntity
                    .badRequest()
                    .build();
        }

        return ResponseEntity
                .noContent()
                .build();
    }

    @PutMapping("reg-forgive/{projectId}")
    public ResponseEntity<?> refuseForgive(@PathVariable int projectId){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));

        int result = projectDetailService.refuseForgive(projectId);

        if (result < 0){
            return ResponseEntity
                    .badRequest()
                    .build();
        }

        return ResponseEntity
                .noContent()
                .build();
    }
}