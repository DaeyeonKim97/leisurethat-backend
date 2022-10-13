package com.steady.leisurethatapi.database.repository;

import com.steady.leisurethatapi.database.entity.Reject;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * <pre>
 * Class : RejectRepository
 * Comment: 클래스에 대한 간단 설명
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-10-07       홍길동           최초 생성
 * </pre>
 *
 * @author 홍길동(최초 작성자)
 * @version 1(클래스 버전)
 * @see
 */
public interface RejectRepository extends JpaRepository<Reject, Integer> {
    public Reject findByJudgeId(int judgeId);
}
