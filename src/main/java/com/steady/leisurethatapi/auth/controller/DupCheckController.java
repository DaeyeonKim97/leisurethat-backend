package com.steady.leisurethatapi.auth.controller;

import com.steady.leisurethatapi.auth.dto.EmailDTO;
import com.steady.leisurethatapi.auth.dto.UsernameDTO;
import com.steady.leisurethatapi.common.dto.ResponseMessage;
import com.steady.leisurethatapi.database.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/duplication")
public class DupCheckController {
    private final MemberRepository memberRepository;

    @Autowired
    public DupCheckController(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @GetMapping("username")
    public ResponseEntity<?> getUserNameIsDuplicated(@RequestBody UsernameDTO usernameDTO){
        System.out.println(usernameDTO.getUsername());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        Map<String, Object> responseMap = new HashMap<>();

        int result = memberRepository.countByUsername(usernameDTO.getUsername());
        responseMap.put("username", usernameDTO.getUsername());

        if (result != 0){
            return ResponseEntity
                    .badRequest()
                    .body(new ResponseMessage(400, "username is duplicated", responseMap));
        } else {
            return ResponseEntity
                    .ok()
                    .body(new ResponseMessage(200, "username is available",responseMap));
        }
    }

    @GetMapping("email")
    public ResponseEntity<?> getEmailIsDuplicated(@RequestBody EmailDTO emailDTO){
        System.out.println(emailDTO.getEmail());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        Map<String, Object> responseMap = new HashMap<>();

        int result = memberRepository.countByEmail(emailDTO.getEmail());
        responseMap.put("email", emailDTO.getEmail());

        if (result != 0){
            return ResponseEntity
                    .badRequest()
                    .body(new ResponseMessage(400, "email is duplicated", responseMap));
        } else {
            return ResponseEntity
                    .ok()
                    .body(new ResponseMessage(200, "email is available",responseMap));
        }
    }
}
