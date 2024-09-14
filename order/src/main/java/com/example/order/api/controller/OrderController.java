package com.example.order.api.controller;

import com.example.order._common.*;
import com.example.order.api.request.*;
import com.example.order.api.response.*;
import com.example.order.app.dto.OrderDto.*;
import com.example.order.app.service.*;
import lombok.*;
import lombok.extern.slf4j.*;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/api/orders")
    public ApiResponse<RegisterOrderRes> registerOrder(@RequestBody RegisterOrderReq request) {
        OrderInfo info = orderService.registerOrder(request.toCommand());
        return ApiResponse.success("주문 등록", new RegisterOrderRes(info.getId()));
    }

    @DeleteMapping("/api/orders/{orderId}")
    public ApiResponse cancelOrder(@PathVariable("orderId") UUID orderId) {
        orderService.cancelOrder(orderId);
        return ApiResponse.success("주문 취소", null);
    }

    @GetMapping("/api/orders/{orderId}")
    public ApiResponse<RetrieveOrderRes> retrieveOrder(@PathVariable("orderId") UUID orderId) {
        OrderInfo info = orderService.retrieveOrder(orderId);
        return ApiResponse.success("주문 단건 조회", RetrieveOrderRes.of(info));
    }

    @GetMapping("/api/orders")
    public ApiResponse<List<RetrieveOrderRes>> retrieveOrderList(Pageable page) {
        List<OrderInfo> orderInfoList = orderService.retrieveOrderList(page);
        List<RetrieveOrderRes> res = orderInfoList.stream().map(RetrieveOrderRes::of).toList();
        return ApiResponse.success("주문 목록 조회", res);
    }
}
