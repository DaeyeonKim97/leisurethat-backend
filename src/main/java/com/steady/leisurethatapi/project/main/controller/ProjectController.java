package com.steady.leisurethatapi.project.main.controller;

import com.steady.leisurethatapi.common.dto.ResponseMessage;
import com.steady.leisurethatapi.project.main.dto.ProjectResponseDTO;
import com.steady.leisurethatapi.project.main.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/project")
public class ProjectController {

    private final ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping
    public ResponseEntity<?> getProjectList(Pageable pageable){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));
        Map<String,Object> responseMap = new HashMap<>();

        List<ProjectResponseDTO> projectList = projectService.getProjectList(pageable);
        responseMap.put("projectList", projectList);

        return ResponseEntity
                .ok()
                .body(new ResponseMessage(200,"success",responseMap));
    }
}
