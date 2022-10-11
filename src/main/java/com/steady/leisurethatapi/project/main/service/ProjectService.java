package com.steady.leisurethatapi.project.main.service;

import com.steady.leisurethatapi.database.entity.Payment;
import com.steady.leisurethatapi.database.entity.Project;
import com.steady.leisurethatapi.database.repository.PaymentRepository;
import com.steady.leisurethatapi.database.repository.ProjectRepository;
import com.steady.leisurethatapi.project.main.dto.ProjectResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final PaymentRepository paymentRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository, PaymentRepository paymentRepository) {
        this.projectRepository = projectRepository;
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
