package com.steady.leisurethatapi.database.repository;

import com.steady.leisurethatapi.database.entity.LoginLog;
import com.steady.leisurethatapi.database.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface MemberRepository extends JpaRepository<Member, Integer> {
    public Member findByUsername(String username);
    public int countByUsername(String username);
    public int countByEmail(String email);

    public Member findById(int id);

    public Member findByNameAndEmail(String name,String email);

    public Member findByNameAndEmailAndUsername(String name, String email, String username);

//    public Page<Member> findAllByIdAndUsername(String keyword, Pageable pageable);

    public Page<Member> findByNameContaining(String keyword, Pageable pageable);


}
