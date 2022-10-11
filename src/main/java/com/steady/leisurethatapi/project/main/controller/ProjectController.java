package com.steady.leisurethatapi.project.main.controller;

import com.steady.leisurethatapi.common.dto.ResponseMessage;
import com.steady.leisurethatapi.database.entity.Project;
import com.steady.leisurethatapi.database.repository.ProjectRepository;
import com.steady.leisurethatapi.project.main.dto.ProjectResponseDTO;
import com.steady.leisurethatapi.project.main.service.ProjectService;
import com.steady.leisurethatapi.project.manage.dto.ProjectDetailResponseDTO;
import com.steady.leisurethatapi.project.manage.service.ProjectDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    private final ProjectDetailService projectDetailService;
    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectController(ProjectService projectService, ProjectDetailService projectDetailService, ProjectRepository projectRepository) {
        this.projectService = projectService;
        this.projectDetailService = projectDetailService;
        this.projectRepository = projectRepository;
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

    @GetMapping("{projectId}")
    public ResponseEntity<?> getProjectDetail(@PathVariable int projectId){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));
        Map<String , Object> responseMap = new HashMap<>();

        ProjectDetailResponseDTO projectDetailResponse = projectDetailService.getProjectDetail(projectId);
        responseMap.put("project", projectDetailResponse);

        Project project = projectRepository.findById(projectId);
        project.setViews(project.getViews() + 1);
        projectRepository.save(project);


        return ResponseEntity
                .ok()
                .body(new ResponseMessage(200,"success",responseMap));
    }
}
