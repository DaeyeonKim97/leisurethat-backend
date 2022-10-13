package com.steady.leisurethatapi.database.repository;

import com.steady.leisurethatapi.database.entity.LoginLog;
import com.steady.leisurethatapi.database.entity.Member;
import com.steady.leisurethatapi.database.entity.Order;
import lombok.extern.java.Log;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoginLogRepository extends JpaRepository<LoginLog, Integer> {

    List<LoginLog> findAllByMemberId(Member member);


}
