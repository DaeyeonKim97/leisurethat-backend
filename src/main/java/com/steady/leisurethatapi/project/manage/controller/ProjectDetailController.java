package com.steady.leisurethatapi.project.manage.controller;

import com.steady.leisurethatapi.common.dto.ResponseMessage;
import com.steady.leisurethatapi.database.entity.Member;
import com.steady.leisurethatapi.database.entity.Project;
import com.steady.leisurethatapi.database.repository.*;
import com.steady.leisurethatapi.project.manage.dto.MakerDetailResponseDTO;
import com.steady.leisurethatapi.project.manage.dto.ProjectDetailResponseDTO;
import com.steady.leisurethatapi.project.manage.service.ProjectDetailService;
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
}
