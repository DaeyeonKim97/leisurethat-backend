package com.steady.leisurethatapi.project.manage.service;

import com.steady.leisurethatapi.database.entity.Product;
import com.steady.leisurethatapi.database.entity.Project;
import com.steady.leisurethatapi.database.entity.Reward;
import com.steady.leisurethatapi.database.entity.Story;
import com.steady.leisurethatapi.database.repository.ProductRepository;
import com.steady.leisurethatapi.database.repository.ProjectRepository;
import com.steady.leisurethatapi.database.repository.RewardRepository;
import com.steady.leisurethatapi.database.repository.StoryRepository;
import com.steady.leisurethatapi.project.manage.dto.ProjectResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectDetailService {
    private final ProjectRepository projectRepository;
    private final StoryRepository storyRepository;
    private final RewardRepository rewardRepository;
    private final ProductRepository productRepository;

    @Autowired
    public ProjectDetailService(ProjectRepository projectRepository, StoryRepository storyRepository, RewardRepository rewardRepository, ProductRepository productRepository) {
        this.projectRepository = projectRepository;
        this.storyRepository = storyRepository;
        this.rewardRepository = rewardRepository;
        this.productRepository = productRepository;
    }

    public ProjectResponseDTO getProjectDetail(int projectId){
        Project project = projectRepository.findById(projectId);
        List<Story> storyList = storyRepository.findByProjectId(projectId);
        List<Product> productList = productRepository.findByProjectId(projectId);
        List<Reward> rewardList = rewardRepository.findByProjectId(projectId);

        return null;
    }
}
