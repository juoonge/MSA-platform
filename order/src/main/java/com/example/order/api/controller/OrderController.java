package com.example.order.api.controller;

import com.example.order._common.*;
import com.example.order._event.*;
import com.example.order.api.request.*;
import com.example.order.api.response.*;
import com.example.order.app.dto.OrderDto.*;
import com.example.order.app.service.*;
import lombok.*;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final OrderRegisterService orderRegisterService;
    private final OrderCancelService orderCancelService;
    private final OrderRetrieveService orderRetrieveService;
    private final KafkaUtils kafkaUtils;

    @PostMapping("/api/orders")
    public ApiResponse<RegisterOrderRes> registerOrder(@RequestBody RegisterOrderReq request) {
        UUID orderId;
        orderId = orderRegisterService.registerOrder(request.toCommand());
        OrderInfo info = orderRetrieveService.retrieveOrder(orderId);
        kafkaUtils.sendEvent("order-registered", info); // todo 이벤트 발행 실패시 처리...
        return ApiResponse.success("주문 등록", new RegisterOrderRes(orderId));
    }

    @PatchMapping("/api/orders/{orderId}/cancel")
    public ApiResponse cancelOrder(@PathVariable("orderId") UUID orderId) {
        orderCancelService.cancelOrder(orderId);
        OrderInfo info = orderRetrieveService.retrieveOrder(orderId);
        kafkaUtils.sendEvent("order-cancel", info);
        return ApiResponse.success("주문 취소");
    }

    @DeleteMapping("/api/orders/{orderId}")
    public ApiResponse removeOrder(@PathVariable("orderId") UUID orderId) {
        orderService.removeOrder(orderId);
        return ApiResponse.success("주문 삭제");
    }

    @GetMapping("/api/orders/{orderId}")
    public ApiResponse<RetrieveOrderRes> retrieveOrder(@PathVariable("orderId") UUID orderId) {
        OrderInfo info = orderRetrieveService.retrieveOrder(orderId);
        RetrieveOrderRes res = RetrieveOrderRes.of(info);
        return ApiResponse.success("주문 단건 조회", res);
    }

    @GetMapping("/api/orders")
    public ApiResponse<List<RetrieveOrderRes>> retrieveOrderList(Pageable page) {
        List<OrderInfo> orderInfoList = orderRetrieveService.retrieveOrderList(page);
        List<RetrieveOrderRes> res = orderInfoList.stream().map(RetrieveOrderRes::of).toList();
        return ApiResponse.success("주문 목록 조회", res);
    }

    // temp
    @GetMapping("/api/test/{deliveryId}/{orderId}")
    public void test(@PathVariable("deliveryId") String deliveryId, @PathVariable("orderId") String orderId) {
        kafkaUtils.sendEvent("delivery-registered", new DeliveryEvent(
                UUID.fromString(deliveryId),
                UUID.fromString(orderId)));
    }
}
