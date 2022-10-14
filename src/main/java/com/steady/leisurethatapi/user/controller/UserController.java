package com.steady.leisurethatapi.user.controller;

import com.steady.leisurethatapi.common.dto.ResponseMessage;
import com.steady.leisurethatapi.user.dto.FundingResponseDTO;
import com.steady.leisurethatapi.user.dto.MakeProjectResponseDTO;
import com.steady.leisurethatapi.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <pre>
 * Class : UserController
 * Comment: 클래스에 대한 간단 설명
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-10-08       전현정           최초 생성
 * </pre>
 *
 * @author 전현정(최초 작성자)
 * @version 1(클래스 버전)
 * @see
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/funding")
    public ResponseEntity<?> getFundingList(@AuthenticationPrincipal UserDetails userDetails, @RequestParam(value="offset", defaultValue = "0") int offset){

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));
        Map<String,Object> responseMap = new HashMap<>();

        Pageable pageable = PageRequest.of(offset, 1, Sort.by("paymentId").descending());

        FundingResponseDTO fundingResponse = userService.selectFundingList(userDetails.getUsername(), pageable);
        int fundingCount = userService.selectFundingCount(userDetails.getUsername());
        responseMap.put("fundingList", fundingResponse);
        responseMap.put("fundingCount", fundingCount);
        return ResponseEntity
                .ok()
                .headers(headers)
                .body(new ResponseMessage(200, "success", responseMap));
    }

    @GetMapping("/project")
    public ResponseEntity<?> getMakeProjectList(@AuthenticationPrincipal UserDetails userDetails, @RequestParam(value="offset", defaultValue = "0") int offset){

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));
        Map<String,Object> responseMap = new HashMap<>();

        Pageable pageable = PageRequest.of(offset, 4, Sort.by("id").descending());

        List<MakeProjectResponseDTO> makeProjectList = userService.selectMakerProjectList(userDetails.getUsername(), pageable);
        int projectCount = userService.selectProjectCount(userDetails.getUsername());
        responseMap.put("makeProjectList", makeProjectList);
        responseMap.put("projectCount", projectCount);
        return ResponseEntity
                .ok()
                .headers(headers)
                .body(new ResponseMessage(200, "make project search success", responseMap));
    }
}
