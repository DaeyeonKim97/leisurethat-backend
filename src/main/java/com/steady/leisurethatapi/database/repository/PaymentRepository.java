package com.steady.leisurethatapi.database.repository;

import com.steady.leisurethatapi.database.entity.Payment;
import com.steady.leisurethatapi.database.entity.SnsCategory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * <pre>
 * Class : PaymentRepository
 * Comment: 결제 쿼리문
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-10-04       전현정           최초 생성
 * </pre>
 *
 * @author 전현정(최초 작성자)
 * @version 1(클래스 버전)
 * @see
 */
public interface PaymentRepository extends JpaRepository<Payment, Integer> {


    public List<Payment> findAllByOrderProjectIdAndOrderOrderStatusAndOrderIdAndOrderMemberName(int projectId, String orderStatus, int id,String sponserName, Pageable pageable);
    public List<Payment> findAllByOrderProjectIdAndOrderOrderStatusAndOrderId(int projectId, String orderStatus, int id, Pageable pageable);
    public List<Payment> findAllByOrderProjectIdAndOrderOrderStatusAndOrderMemberName(int projectId, String orderStatus, String sponserName, Pageable pageable);

    public List<Payment> findAllByOrderProjectIdAndOrderOrderStatus(int projectId, String orderStatus, Pageable pageable);

}
