package com.steady.leisurethatapi.project.enroll.controller;

import com.steady.leisurethatapi.common.dto.ResponseMessage;
import com.steady.leisurethatapi.database.entity.*;
import com.steady.leisurethatapi.database.repository.*;
import com.steady.leisurethatapi.project.enroll.vo.ProjectEnrollVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/project/enroll")
public class ProjectEnrollController {
    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;
    private final ProjectRepository projectRepository;
    private final RewardRepository rewardRepository;
    private final StoryRepository storyRepository;
    private final AccountInfoRepository accountInfoRepository;
    private final BankRepository bankRepository;
    private final BusinessInfoRepository businessInfoRepository;
    private final ProjectCategoryRepository projectCategoryRepository;
    private final ProjectStatusRepository projectStatusRepository;


    @Autowired
    public ProjectEnrollController(MemberRepository memberRepository, ProductRepository productRepository, ProjectRepository projectRepository, RewardRepository rewardRepository, StoryRepository storyRepository, AccountInfoRepository accountInfoRepository, BankRepository bankRepository, BusinessInfoRepository businessInfoRepository, ProjectCategoryRepository projectCategoryRepository, ProjectStatusRepository projectStatusRepository) {
        this.memberRepository = memberRepository;
        this.productRepository = productRepository;
        this.projectRepository = projectRepository;
        this.rewardRepository = rewardRepository;
        this.storyRepository = storyRepository;
        this.accountInfoRepository = accountInfoRepository;
        this.bankRepository = bankRepository;
        this.businessInfoRepository = businessInfoRepository;
        this.projectCategoryRepository = projectCategoryRepository;
        this.projectStatusRepository = projectStatusRepository;
    }

    @GetMapping
    public ResponseEntity<?> getEnrollInfo(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));
        Map<String,Object> responseMap = new HashMap<>();
        responseMap.put("bank", bankRepository.findAllBy());
        responseMap.put("category", projectCategoryRepository.findAllBy());

        return ResponseEntity
                .ok()
                .body(new ResponseMessage(200,"success",responseMap));
    }

    @PostMapping
    public ResponseEntity<?> enrollProject(ProjectEnrollVO vo){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));
        Map<String,Object> responseMap = new HashMap<>();
        String username = null;
        try{
            username = SecurityContextHolder.getContext().getAuthentication().getName();
        } catch (Exception e){
            return ResponseEntity
                    .badRequest()
                    .build();
        }
        Member member = memberRepository.findByUsername(username);

        Bank bank = bankRepository.findById(vo.getBankId());

        Attachment attachment = new Attachment();

//        AccountInfo accountInfo = new AccountInfo(0,vo.getAccountNumber(),vo.getAccountHolder(),);
        BusinessInfo businessInfo = new BusinessInfo();

        ProjectCategory projectCategory = projectCategoryRepository.findById(vo.getCategoryId());
        ProjectStatus projectStatus = projectStatusRepository.findById(1);
        Project project = new Project();


        return null;
    }
}
