package com.steady.leisurethatapi.member.Service;

import com.steady.leisurethatapi.database.entity.Member;
import com.steady.leisurethatapi.database.repository.MemberRepository;
import com.steady.leisurethatapi.member.dto.MatchIdDTO;
import com.steady.leisurethatapi.member.dto.MatchPasswordDTO;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public MemberService(MemberRepository memberRepository, PasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Object findById(int id) {
        Member member = memberRepository.findById(id);
        return member;
    }

    @Transactional
    public int deleteUser(String username) {

        Member foundMember = null;

        if(memberRepository.findByUsername(username)!=null){
            foundMember = memberRepository.findByUsername(username);


            String tempPassword = UUID.randomUUID()
                    .toString()
                    .replace("-", "")
                    .substring(0,10);

            String password = passwordEncoder.encode(tempPassword);

            foundMember.setSecYn("Y");
            foundMember.setPassword(password);
            return 1;
        }

        return 0;
    }

    @Transactional
    public void PutUser(int id ,Member member) {
        Member foundMember = memberRepository.findById(id);
        foundMember.setName(member.getName());
    }

    public String matchUsername(MatchIdDTO matchIdDTO) {
        Member foundMember = memberRepository.findByNameAndEmail(matchIdDTO.getName(), matchIdDTO.getEmail());
        return foundMember.getUsername();
    }

    @Transactional
    public String matchPassword(MatchPasswordDTO matchPasswordDTO) {
        Member foundMember = memberRepository.findByNameAndEmailAndUsername(matchPasswordDTO.getName(), matchPasswordDTO.getEmail(),matchPasswordDTO.getUsername());

        String tempPassword = UUID.randomUUID()
                                .toString()
                                .replace("-", "")
                                .substring(0,10);

        String password = passwordEncoder.encode(tempPassword);
        foundMember.setPassword(password);

        return tempPassword;
    }

    public Object getUserByUsername(String username) {
        Member member = memberRepository.findByUsername(username);
        return member;
    }

    @Transactional

    public void putPassword(String username, String oldPassword, String newPassword) {
        Member foundMember = memberRepository.findByUsername(username);
        System.out.println(foundMember);


        if(!passwordEncoder.matches(oldPassword,foundMember.getPassword())){
            System.out.println("비밀번호가 일치하지 않습니다.");
            return;
        }

        foundMember.setPassword(passwordEncoder.encode(newPassword));
        System.out.println("비밀번호가 변경되었습니다.");

    }

//    public void postUserByAdmin(RegistUserDTO registUserDTO) {
//        memberRepository.save(new Member(
//                0,
//                registUserDTO.getUsername(),
//                passwordEncoder.encode(registUserDTO.getPassword()),
//                "N",
//                new java.sql.Date(new Date().getTime()),
//                registUserDTO.getEmail(),
//                null,
//                registUserDTO.getName(),
//                null,
//                snsCategoryRepository.findByid(1),
//                registUserDTO.getPhone(),
//                null,
//
//
//        ));
//    }



//    member = memberRepository.save(new Member(
//                    0,
//                    signUp.getUsername(),
//                    passwordEncoder.encode(signUp.getPassword()),
//                            "N",
//                            new java.sql.Date(new Date().getTime()),
//            signUp.getEmail(),
//            null,
//            signUp.getName(),
//            null,
//            snsCategoryRepository.findById(1),
//            null,
//            null,
//            "USER"
//            ));
}
