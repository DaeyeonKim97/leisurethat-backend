package com.steady.leisurethatapi.calculate.service;

import com.steady.leisurethatapi.calculate.dto.CalculateApplicationStatusDTO;
import com.steady.leisurethatapi.database.entity.Calculate;
import com.steady.leisurethatapi.database.repository.CalculateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <pre>
 * Class : CalculateService
 * Comment: 클래스에 대한 간단 설명
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-10-06       전현정           최초 생성
 * </pre>
 *
 * @author 전현정(최초 작성자)
 * @version 1(클래스 버전)
 * @see
 */
@Service
public class CalculateService {

    private final CalculateRepository calculateRepository;

    @Autowired
    public CalculateService(CalculateRepository calculateRepository) {
        this.calculateRepository = calculateRepository;
    }

    public List<CalculateApplicationStatusDTO> selectCalculateApplicationList(int projectId, Pageable pageable) {

        List<Calculate> calculates = calculateRepository.findAllByJudgeProjectId(projectId, pageable);

        return null;
    }
}
