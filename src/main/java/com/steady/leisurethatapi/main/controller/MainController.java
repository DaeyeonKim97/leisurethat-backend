package com.steady.leisurethatapi.main.controller;

import com.steady.leisurethatapi.common.dto.ResponseMessage;
import com.steady.leisurethatapi.database.entity.*;
import com.steady.leisurethatapi.database.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/main")
public class MainController {

    private final ProjectRepository projectRepository;
    private final ProjectCategoryRepository projectCategoryRepository;
    private final ProjectStatusRepository projectStatusRepository;
    private final OrderDeliveryRepositroy orderDeliveryRepository;
    private final OrderRepository orderRepository;
    @Autowired
    public MainController(ProjectRepository projectRepository,
                          ProjectCategoryRepository projectCategoryRepository,
                          ProjectStatusRepository projectStatusRepository,
                          OrderDeliveryRepositroy orderDeliveryRepository,
                          OrderRepository orderRepository){
        this.projectRepository = projectRepository;
        this.projectCategoryRepository = projectCategoryRepository;
        this.projectStatusRepository = projectStatusRepository;
        this.orderDeliveryRepository = orderDeliveryRepository;
        this.orderRepository = orderRepository;
    }

    @GetMapping
    public ResponseEntity<?> mainPage(@RequestParam(name = "categoryId") int categoryId){

        // 프로젝트에서 가져와야함
        ProjectCategory projectCategory = projectCategoryRepository.findById(categoryId);
        // 모집중인 것 만 조회
        ProjectStatus projectStatus = projectStatusRepository.findById(5);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        Map<String,Object> responseMap = new HashMap<>();

        responseMap.put("projects",projectRepository.findByProjectCategoryAndStatus(projectCategory, projectStatus));
        System.out.println(projectRepository.findByProjectCategoryAndStatus(projectCategory, projectStatus));

        return ResponseEntity.ok().body(new ResponseMessage(200, "success",responseMap));
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<?> deliveryApi(@PathVariable(name = "orderId") int orderId){
        OrderDelivery orderDelivery = orderDeliveryRepository.findByOrderId(orderId);
        Map<String, Object> result = new HashMap<>();
        result.put("orderDelivery", orderDelivery);

        return ResponseEntity.ok().body(new ResponseMessage(200, "success", result));

    }

}
