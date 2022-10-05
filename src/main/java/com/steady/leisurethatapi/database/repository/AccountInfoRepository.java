package com.steady.leisurethatapi.database.repository;

import com.steady.leisurethatapi.database.entity.AccountInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountInfoRepository extends JpaRepository<AccountInfo, Integer> {

}
