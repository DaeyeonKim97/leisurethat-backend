package com.steady.leisurethatapi.database.repository;

import com.steady.leisurethatapi.database.entity.Calculate;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * <pre>
 * Class : CalculateRepository
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

public interface CalculateRepository extends JpaRepository<Calculate, Integer> {

    public List<Calculate> findAllByJudgeProjectId(int projectId, Pageable pageable);
    public List<Calculate> findAllByJudgeProjectId(int projectId, Sort sort);
    public Calculate findById(int id);
}
