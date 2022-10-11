package com.steady.leisurethatapi.project.main.service;

import com.steady.leisurethatapi.database.entity.Payment;
import com.steady.leisurethatapi.database.entity.Project;
import com.steady.leisurethatapi.database.repository.*;
import com.steady.leisurethatapi.project.main.dto.ProjectResponseDTO;
import com.steady.leisurethatapi.project.manage.dto.ProjectDetailResponseDTO;
import com.steady.leisurethatapi.project.manage.service.ProjectDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final StoryRepository storyRepository;
    private final RewardRepository rewardRepository;
    private final ProductRepository productRepository;
    private final PaymentRepository paymentRepository;


    @Autowired
    public ProjectService(ProjectRepository projectRepository, StoryRepository storyRepository, RewardRepository rewardRepository, ProductRepository productRepository, PaymentRepository paymentRepository) {
        this.projectRepository = projectRepository;
        this.storyRepository = storyRepository;
        this.rewardRepository = rewardRepository;
        this.productRepository = productRepository;
        this.paymentRepository = paymentRepository;
    }

    public List<ProjectResponseDTO> getProjectList(Pageable pageable){
        List<ProjectResponseDTO> response = new ArrayList<>();
        List<Project> projectList = projectRepository.findByStatusId(5,pageable);

        for(Project project : projectList){
            ProjectResponseDTO item = new ProjectResponseDTO(project);

            int sum = 0;
            List<Payment> paymentList = paymentRepository.findByOrderProjectId(project.getId());
            for(Payment payment : paymentList){
                sum += payment.getPrice();
            }
            item.setTotalAmount(sum);

            response.add(item);
        }

        return response;
    }

}
