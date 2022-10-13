package com.steady.leisurethatapi.order.controller;

import com.steady.leisurethatapi.common.dto.ResponseMessage;
import com.steady.leisurethatapi.order.dto.OrderCompleteDTO;
import com.steady.leisurethatapi.order.dto.OrderInfoDTO;
import com.steady.leisurethatapi.order.dto.OrderUserInfoDTO;
import com.steady.leisurethatapi.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <pre>
 * Class : OrderController
 * Comment: 주문 컨트롤러
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-10-04       전현정           최초 생성
 * </pre>
 *
 * @author 전현정(최초 작성자)
 * @version 1(클래스 버전)
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/cancled")
    public ResponseEntity<?> getOrderCancleList(@RequestParam("projectId") int projectId, @RequestParam(value="sponserName", required = false) String sponserName, @RequestParam(value="id", defaultValue = "0") int id,
                                                                 @RequestParam(value="offset", defaultValue="0") int offset) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));
        Map<String,Object> responseMap = new HashMap<>();

        String orderStatus = "주문 취소";
        Pageable pageable = PageRequest.of(offset, 6, Sort.by("order.id").descending());

        int count = orderService.selectOrderCancleListCount(projectId, id, sponserName, orderStatus);
        int pageCount = count/6;
        List<OrderInfoDTO> cancleList = orderService.selectOrderCancleList(projectId, id, sponserName, orderStatus, pageable);

        responseMap.put("count", count);
        responseMap.put("pageCount", pageCount);
        responseMap.put("cancleList", cancleList);
        return ResponseEntity
                .ok()
                .headers(headers)
                .body(new ResponseMessage(200, "order cancle list search success", responseMap));
    }



    @GetMapping("/{id}/user")
    public ResponseEntity<?> getOrderCancleUserInfo(@PathVariable("id") int id) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));
        Map<String,Object> responseMap = new HashMap<>();

        OrderUserInfoDTO userInfo = orderService.selectOrderCancleUserInfoByOrderId(id);

        System.out.println(userInfo);

        responseMap.put("userInfo", userInfo);

        return ResponseEntity
                .ok()
                .headers(headers)
                .body(new ResponseMessage(200, "order cancle userinfo search success", responseMap));
    }

    @GetMapping("/waiting")
    public ResponseEntity<?> getOrderWaitingList(@RequestParam("projectId") int projectId, @RequestParam(value="sponserName", required = false) String sponserName, @RequestParam(value="id", defaultValue = "0") int id,
                                                @RequestParam(value="offset", defaultValue="0") int offset) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));
        Map<String,Object> responseMap = new HashMap<>();

        String orderStatus = "결제 대기";
        Pageable pageable = PageRequest.of(offset, 6, Sort.by("order.id").descending());


        List<OrderInfoDTO> waitingList = orderService.selectOrderWaitingList(projectId, id, sponserName, orderStatus, pageable);


        responseMap.put("waitingList", waitingList);

        return ResponseEntity
                .ok()
                .headers(headers)
                .body(new ResponseMessage(200, "order waiting list search success", responseMap));
    }

    @GetMapping("/complete")
    public ResponseEntity<?> getOrderCompleteList(@RequestParam("projectId") int projectId, @RequestParam(value="sponserName", required = false) String sponserName, @RequestParam(value="id", defaultValue = "0") int id,
                                                 @RequestParam(value="offset", defaultValue="0") int offset) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));
        Map<String,Object> responseMap = new HashMap<>();

        String orderStatus = "결제 완료";
        Pageable pageable = PageRequest.of(offset, 6, Sort.by("order.id").descending());


        List<OrderCompleteDTO> completeList = orderService.selectOrderCompleteList(projectId, id, sponserName, orderStatus, pageable);


        responseMap.put("completeList", completeList);

        return ResponseEntity
                .ok()
                .headers(headers)
                .body(new ResponseMessage(200, "order complete list search success", responseMap));
    }

    @PostMapping("/waybill")
    public ResponseEntity<?> postWaybill(@RequestBody OrderCompleteDTO newWaybill){
        orderService.postWaybill(newWaybill);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));
        Map<String,Object> responseMap = new HashMap<>();

        return ResponseEntity
                .ok()
                .headers(headers)
                .body(new ResponseMessage(200, "waybill post success", responseMap));
    }

}
