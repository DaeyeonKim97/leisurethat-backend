package com.steady.leisurethatapi.database.repository;

import com.steady.leisurethatapi.database.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Integer> {
    public Member findByUsername(String username);
}