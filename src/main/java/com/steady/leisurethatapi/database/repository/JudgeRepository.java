package com.steady.leisurethatapi.database.repository;

import com.steady.leisurethatapi.database.entity.Judge;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * <pre>
 * Class : JudgeRepository
 * Comment: Judge 테이블 repository
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-10-09       전현정           최초 생성
 * </pre>
 *
 * @author 전현정(최초 작성자)
 * @version 1(클래스 버전)
 * @see
 */
public interface JudgeRepository extends JpaRepository<Judge, Integer> {



}
