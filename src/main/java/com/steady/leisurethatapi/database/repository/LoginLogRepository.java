package com.steady.leisurethatapi.database.repository;

import com.steady.leisurethatapi.database.entity.LoginLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginLogRepository extends JpaRepository<LoginLog, Integer> {
}
