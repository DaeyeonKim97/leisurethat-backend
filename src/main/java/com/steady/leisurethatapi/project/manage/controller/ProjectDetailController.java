package com.steady.leisurethatapi.project.manage.controller;

import com.steady.leisurethatapi.database.entity.Project;
import com.steady.leisurethatapi.database.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.print.Pageable;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("project-detail")
public class ProjectDetailController {

    private final ProjectRepository projectRepository;
    private final MemberRepository memberRepository;
    private final StoryRepository storyRepository;
    private final RewardRepository rewardRepository;
    private final OrderRepository orderRepository;

    @Autowired
    public ProjectDetailController(ProjectRepository projectRepository, MemberRepository memberRepository, StoryRepository storyRepository, RewardRepository rewardRepository, OrderRepository orderRepository) {
        this.projectRepository = projectRepository;
        this.memberRepository = memberRepository;
        this.storyRepository = storyRepository;
        this.rewardRepository = rewardRepository;
        this.orderRepository = orderRepository;
    }


    @GetMapping("{projectId}")
    public ResponseEntity<?> getProjectDetailInfo(@PathVariable int projectId, Pageable pageable){
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
        if(project == null){
            return ResponseEntity
                    .badRequest()
                    .build();
        }
        if(project.getBusinessInfo().getMember().getUsername() != username){
            return ResponseEntity
                    .status(401)
                    .build();
        }

        return null;
    }
}
