package com.steady.leisurethatapi.database.repository;
import com.steady.leisurethatapi.calculate.dto.CalculateAmountResultDTO;
import com.steady.leisurethatapi.database.entity.Order;
import com.steady.leisurethatapi.database.entity.Payment;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {

    public Payment findByOrderAndCardTokenIsNotNull(Order order);

    public List<Payment> findAllByOrderProjectIdAndOrderStatusAndOrderIdAndOrderMemberName(int projectId, String orderStatus, int id,String sponserName, Pageable pageable);
    public List<Payment> findAllByOrderProjectIdAndOrderStatusAndOrderId(int projectId, String orderStatus, int id, Pageable pageable);
    public List<Payment> findAllByOrderProjectIdAndOrderStatusAndOrderMemberName(int projectId, String orderStatus, String sponserName, Pageable pageable);
    public List<Payment> findAllByOrderProjectIdAndOrderStatus(int projectId, String orderStatus, Pageable pageable);
    @Query( value = "SELECT \n" +
            "new com.steady.leisurethatapi.calculate.dto.CalculateAmountResultDTO(SUM(A.paymentPrice), (SELECT SUM(B.paymentPrice) FROM Payment B WHERE B.order.project.id = :projectId AND B.paymentStatus = '결제 완료' GROUP BY (B.order.project.id))) \n" +
            "  FROM Payment A \n" +
            " WHERE A.order.project.id = :projectId \n" +
            "   AND A.paymentStatus NOT IN ('주문 취소') \n" +
            " GROUP BY(A.order.project.id)")
    public CalculateAmountResultDTO findPaymentSum(int projectId);
    @Query(value = "SELECT \n" +
            "SUM(p.paymentPrice) FROM Payment p \n" +
            "WHERE p.order.project.id = :projectId \n" +
            "AND p.order.status != '주문 취소' \n" +
            "GROUP BY(p.order.project.id)")
    int findSumSupportAmountByProjectId(int projectId);
    @Query(value = "SELECT \n" +
            "COUNT(p) FROM Payment p \n" +
            "WHERE p.order.project.id = :projectId \n" +
            "AND p.order.status = '결제 완료'")
    int findSupportCount(int projectId);

    List<Payment> findAllByOrderMemberUsername(String username, Pageable pageable);

    @Query(value = "SELECT \n" +
            "count(*) FROM Payment p \n" +
            "WHERE p.order.project.id = :projectId \n" +
            "AND p.paymentStatus = :orderStatus \n" +
            "AND p.order.id = :id \n" +
            "AND p.order.member.name = :sponserName")
    int findCancleCountByOrderProjectIdAndOrderStatusAndOrderIdAndOrderMemberName(int projectId, String orderStatus, int id, String sponserName);

    @Query(value = "SELECT \n" +
            "count(*) FROM Payment p \n" +
            "WHERE p.order.project.id = :projectId \n" +
            "AND p.paymentStatus = :orderStatus \n" +
            "AND p.order.id = :id")
    int findCancleCountByOrderProjectIdAndOrderStatusAndOrderId(int projectId, String orderStatus, int id);

    @Query(value = "SELECT \n" +
            "count(*) FROM Payment p \n" +
            "WHERE p.order.project.id = :projectId \n" +
            "AND p.paymentStatus = :orderStatus \n" +
            "AND p.order.member.name = :sponserName")
    int findCancleCountByOrderProjectIdAndOrderStatusAndOrderMemberName(int projectId, String orderStatus, String sponserName);


    @Query(value = "SELECT \n" +
            "count(*) FROM Payment p \n" +
            "WHERE p.order.project.id = :projectId \n" +
            "AND p.paymentStatus = :orderStatus")
    int findCancleCountByOrderProjectIdAndOrderStatus(int projectId, String orderStatus);

    public List<Payment> findByOrderProjectId(int projectId);
}