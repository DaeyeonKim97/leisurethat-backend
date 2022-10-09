package com.steady.leisurethatapi.project.manage.service;

import com.steady.leisurethatapi.database.entity.Product;
import com.steady.leisurethatapi.database.entity.Project;
import com.steady.leisurethatapi.database.entity.Reward;
import com.steady.leisurethatapi.database.entity.Story;
import com.steady.leisurethatapi.database.repository.ProductRepository;
import com.steady.leisurethatapi.database.repository.ProjectRepository;
import com.steady.leisurethatapi.database.repository.RewardRepository;
import com.steady.leisurethatapi.database.repository.StoryRepository;
import com.steady.leisurethatapi.project.manage.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public ProjectDetailResponseDTO getProjectDetail(int projectId){
        ProjectDetailResponseDTO response = new ProjectDetailResponseDTO();
        System.out.println("???");
        Project project = projectRepository.findById(projectId);
        System.out.println("project : " + project);
        List<Story> storyList = storyRepository.findByProjectId(projectId);
        List<Product> productList = productRepository.findByProjectId(projectId);
        List<Reward> rewardList = rewardRepository.findByProjectId(projectId);

        AccountInfoResponseDTO accountInfoResponse = new AccountInfoResponseDTO(project.getAccountInfo());
        BusinessInfoResponseDTO businessInfoResponse = new BusinessInfoResponseDTO(project.getBusinessInfo());
        MemberResponseDTO memberResponse = new MemberResponseDTO(project.getBusinessInfo().getMember());
        ProjectResponseDTO projectResponse = new ProjectResponseDTO(project);
        List<StoryResponseDTO> storyListResponse = new ArrayList<>();
        for(Story story : storyList){
            storyListResponse.add(new StoryResponseDTO(story));
        }
        List<ProductResponseDTO> productListResponse = new ArrayList<>();
        for(Product product : productList){
            productListResponse.add(new ProductResponseDTO(product));
        }
        List<RewardResponseDTO> rewardListResponse = new ArrayList<>();
        for (Reward reward : rewardList){
            rewardListResponse.add(new RewardResponseDTO(reward));
        }

        response.setProject(projectResponse);
        response.setMember(memberResponse);
        response.setAccountInfo(accountInfoResponse);
        response.setBusinessInfo(businessInfoResponse);
        response.setStoryList(storyListResponse);
        response.setRewardList(rewardListResponse);
        response.setProductList(productListResponse);

        return response;
    }
}
