package com.steady.leisurethatapi.member.controller;
import com.steady.leisurethatapi.auth.service.MailService;
import com.steady.leisurethatapi.auth.util.JwtUtil;
import com.steady.leisurethatapi.common.dto.ResponseMessage;
import com.steady.leisurethatapi.database.entity.LoginLog;
import com.steady.leisurethatapi.database.entity.Member;
import com.steady.leisurethatapi.database.repository.LoginLogRepository;
import com.steady.leisurethatapi.database.repository.MemberRepository;
import com.steady.leisurethatapi.member.Service.MemberService;
import com.steady.leisurethatapi.member.dto.MatchIdDTO;
import com.steady.leisurethatapi.member.dto.MatchPasswordDTO;
import com.steady.leisurethatapi.member.dto.PasswordDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class MemberController {

    private final MemberRepository memberRepository;

    private final AuthenticationManager authenticationManager;

    private final MemberService memberService;
    private final LoginLogRepository loginLogRepository;
    private final MailService mailService;

    @Autowired
    public MemberController(MemberRepository memberRepository, JwtUtil jwtUtil, AuthenticationManager authenticationManager, MemberService memberService, LoginLogRepository loginLogRepository, MailService mailService) {
        this.memberRepository = memberRepository;
        this.authenticationManager = authenticationManager;
        this.memberService = memberService;
        this.loginLogRepository = loginLogRepository;
        this.mailService = mailService;
    }


//    @GetMapping("")
//    public ResponseEntity<?> getUserListByPage(@RequestParam String keyword,  @PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));
//        Map<String,Object> responseMap = new HashMap<>();
//
//       Page<Member> memberList = memberRepository.findAll(PageRequest.of(page, 5));
//
//        Page<Member> memberList = memberRepository.findAllByIdAndUsername(keyword,pageable);
//        responseMap.put("memberList", memberList);
//
//        return ResponseEntity
//                .ok()
//                .headers(headers)
//                .body(new ResponseMessage(200, "OK",responseMap));
//
//    }


    @GetMapping("")
    public ResponseEntity<?> getUserListByPage(@RequestParam String keyword,  @PageableDefault(size = 5, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));
        Map<String,Object> responseMap = new HashMap<>();


        Page<Member> memberList = memberRepository.findByNameContaining(keyword,pageable);
        responseMap.put("memberList", memberList);

        return ResponseEntity
                .ok()
                .headers(headers)
                .body(new ResponseMessage(200, "OK",responseMap));

    }



//    @GetMapping("/{id}")
//    public Member getUserById(@PathVariable int id) {
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));
//        Map<String,Object> responseMap = new HashMap<>();
//        Member member = (Member) memberService.findById(id);
//
//        return member;
//    }

    @GetMapping("/{username}")
    public ResponseEntity<?> getUserByUsername(@PathVariable String username) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));
        Map<String,Object> responseMap = new HashMap<>();
        Member member = (Member) memberService.getUserByUsername(username);

        responseMap.put("member", member);

        return ResponseEntity
                .ok()
                .headers(headers)
                .body(new ResponseMessage(200, "OK",responseMap));
    }

//    @PostMapping("regist")
//    public void postUserByAdmin(@RequestBody RegistUserDTO registUserDTO) {
//        memberService.postUserByAdmin(registUserDTO);
//    }


    @PostMapping("match/id")
    public void matchUsername(@RequestBody MatchIdDTO matchIdDTO) throws MessagingException {


        System.out.println("입력된 이름 : " + matchIdDTO.getName());
        System.out.println("입력된 이메일 : " + matchIdDTO.getEmail());

        String username = memberService.matchUsername(matchIdDTO);

        StringBuilder convertedUsername = new StringBuilder();
        convertedUsername.append(username);
        convertedUsername.replace(username.length()-3,username.length(),"***");
        System.out.println("사용자 아이디 : " + convertedUsername);

        System.out.println(matchIdDTO.getEmail() + "로 메일을 발송합니다.");
        mailService.sendEmailForMatchId(matchIdDTO.getEmail(),convertedUsername.toString());

    }


    @PostMapping("match/password")
    public void matchPassword(@RequestBody MatchPasswordDTO matchPasswordDTO) throws MessagingException {
        System.out.println("입력된 이름 : " + matchPasswordDTO.getName());
        System.out.println("입력된 아이디 : " + matchPasswordDTO.getUsername());
        System.out.println("입력된 이메일 : " + matchPasswordDTO.getEmail());

        String tempPassword = memberService.matchPassword(matchPasswordDTO);
        System.out.println(matchPasswordDTO.getEmail() + "로 메일을 발송합니다.");
        mailService.sendEmailForMatchPassword(matchPasswordDTO.getEmail(),tempPassword);

    }


    @GetMapping("/count")
    public ResponseEntity<?> userCount() {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));
        Map<String,Object> responseMap = new HashMap<>();

        Long count = memberRepository.count();


        responseMap.put("count", count);

        return ResponseEntity
                .ok()
                .headers(headers)
                .body(new ResponseMessage(200, "OK",responseMap));
    }

    @GetMapping("/loginlog/{id}")
    public ResponseEntity getLoginLog(@PathVariable int id) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));
        Map<String,Object> responseMap = new HashMap<>();

        Member member = memberRepository.findById(id);

        List<LoginLog> loginLog =  loginLogRepository.findAllByMemberId(member);
        responseMap.put("loginLog", loginLog);

        return ResponseEntity
                .ok()
                .headers(headers)
                .body(new ResponseMessage(200, "OK",responseMap));

    }

    @PutMapping("/password")
    public  void putPassword(@RequestBody PasswordDTO passwordDTO){
            memberService.putPassword(passwordDTO.getUsername(),passwordDTO.getOldPassword(),passwordDTO.getNewPassword());
    }

    @PutMapping("/{id}")
    public void PutUesr(@PathVariable int id, @RequestBody Member member){
        memberService.PutUser(id,member);
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<?> deleteUesr(@PathVariable String username){

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));
        Map<String,Object> responseMap = new HashMap<>();

        int result = memberService.deleteUser(username);

        responseMap.put("result", result);

        if(result>0){
            return ResponseEntity
                    .ok()
                    .headers(headers)
                    .body(new ResponseMessage(200, "OK",responseMap));

        }

        return ResponseEntity
                .badRequest()
                .headers(headers)
                .body(new ResponseMessage(400, "Bad Request",responseMap));




    }



}
