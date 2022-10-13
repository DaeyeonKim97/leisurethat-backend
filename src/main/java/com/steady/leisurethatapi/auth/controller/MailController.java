package com.steady.leisurethatapi.auth.controller;


import com.steady.leisurethatapi.auth.dto.EmailDTO;
import com.steady.leisurethatapi.auth.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;

@RestController
@RequestMapping("/mail")
public class MailController {

    private final MailService mailService;

    @Autowired
    public MailController(MailService mailService) {
        this.mailService = mailService;
    }

    @PostMapping("/match")
    public int sendEmail(@RequestBody EmailDTO toEmail) throws MessagingException {
        System.out.println(toEmail.getEmail() + "로 메일을 발송합니다.");
        int certCode = mailService.sendEmail(toEmail.getEmail());
        return certCode;
    }

}
