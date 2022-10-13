package com.steady.leisurethatapi.project.enroll.controller;

import com.steady.leisurethatapi.attachment.service.UploadS3Service;
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

import java.io.IOException;
import java.net.URI;
import java.nio.charset.Charset;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    private final UploadS3Service uploadS3Service;


    @Autowired
    public ProjectEnrollController(MemberRepository memberRepository, ProductRepository productRepository, ProjectRepository projectRepository, RewardRepository rewardRepository, StoryRepository storyRepository, AccountInfoRepository accountInfoRepository, BankRepository bankRepository, BusinessInfoRepository businessInfoRepository, ProjectCategoryRepository projectCategoryRepository, ProjectStatusRepository projectStatusRepository, UploadS3Service uploadS3Service) {
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
        this.uploadS3Service = uploadS3Service;
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
    public ResponseEntity<?> enrollProject(ProjectEnrollVO vo) throws ParseException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));
        Map<String,Object> responseMap = new HashMap<>();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        String username = null;
        try{
            username = SecurityContextHolder.getContext().getAuthentication().getName();
        } catch (Exception e){
            return ResponseEntity
                    .badRequest()
                    .build();
        }
        Member member = memberRepository.findByUsername(username);

        //
        try {
            System.out.println(vo.getAccountFile().getInputStream().readAllBytes().toString());
            System.out.println("bytes : "+ vo.getAccountFile().getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //

        Attachment businessAttachment = uploadS3Service.uploadFile("business",vo.getBusinessFile()[0]);
        Attachment accountAttachment = uploadS3Service.uploadFile("account", vo.getAccountFile());

        BusinessInfo businessInfo = new BusinessInfo(0,businessAttachment,member,vo.getTaxInvoiceEmail());
        businessInfoRepository.save(businessInfo);
        Bank bank = bankRepository.findById(vo.getBankId());
        AccountInfo accountInfo = new AccountInfo(0,vo.getAccountNumber(),vo.getAccountHolder(),accountAttachment,businessInfo,bank);
        accountInfoRepository.save(accountInfo);

        ProjectCategory projectCategory = projectCategoryRepository.findById(vo.getCategoryId());
        ProjectStatus projectStatus = projectStatusRepository.findById(1);
        Attachment projectAttachment = uploadS3Service.uploadFile("project",vo.getProjectFile());
        Project project = new Project(
            0,
                vo.getName(),
                new Date((formatter.parse(vo.getStartDate())).getTime()),
                new Date((formatter.parse(vo.getEndDate())).getTime()),
                vo.getTargetAmount(),
                vo.getUrl(),
                projectCategory,
                0,
                projectStatus,
                businessInfo,
                accountInfo,
                projectAttachment,
                vo.getRefundPolicy(),
                vo.getInquiryEmail(),
                vo.getInquiryPhone()
        );
        projectRepository.save(project);
        Attachment storyAttachment = uploadS3Service.uploadFile("story",vo.getStoryFile());
        Story story = new Story(
            0,
                project,
                vo.getStoryTitle(),
                vo.getStoryContent(),
                0,
                new Date(new java.util.Date().getTime()),
                "Y",
                storyAttachment
        );
        storyRepository.save(story);

        Attachment productAttachment = uploadS3Service.uploadFile("product",vo.getProductFile());
        Product product = new Product(0, vo.getProductName(), project, productAttachment, vo.getProductDetail());
        productRepository.save(product);

        Reward reward = new Reward(0,
                vo.getRewardPrice() ,
                vo.getRewardTitle(),
                vo.getRewardServeCount(),
                vo.getRewardMaxCount(),
                new Date((formatter.parse(vo.getRewardDate())).getTime()),
                vo.getRewardFee(),
                vo.getRewardFeeFar(),
                project,
                vo.getRewardContent()
        );
        rewardRepository.save(reward);

        responseMap.put("reward", reward);

        return ResponseEntity
                .created(URI.create("/project"))
                .headers(headers)
                .body(new ResponseMessage(201, "success",responseMap));
    }
}
