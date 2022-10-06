package com.steady.leisurethatapi.calculate.controller;

import com.steady.leisurethatapi.calculate.dto.CalculateApplicationStatusDTO;
import com.steady.leisurethatapi.calculate.service.CalculateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
 * Class : CalculateController
 * Comment: 클래스에 대한 간단 설명
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-10-06       홍길동           최초 생성
 * </pre>
 *
 * @author 홍길동(최초 작성자)
 * @version 1(클래스 버전)
 * @see
 */
@RestController
@RequestMapping("/calculate")
public class CalculateController {

    private final CalculateService calculateService;

    @Autowired
    public CalculateController(CalculateService calculateService){
        this.calculateService = calculateService;
    }

    @GetMapping("/")
    public ResponseEntity<?> getCalculateApplicationList(@RequestParam("projectId") int projectId, @RequestParam("offset") int offset) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));
        Map<String,Object> responseMap = new HashMap<>();

        Pageable pageable = PageRequest.of(offset, 6, Sort.by("id").descending());

        List<CalculateApplicationStatusDTO> calculateApplicationList = calculateService.selectCalculateApplicationList(projectId, pageable);




        return null;
    }
}
