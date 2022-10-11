package com.steady.leisurethatapi.database.repository;

import com.steady.leisurethatapi.database.entity.BusinessInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusinessInfoRepository extends JpaRepository<BusinessInfo, Integer> {
}
