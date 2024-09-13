package com.sparta.delivery_service.presentation;

import com.sparta.delivery_service.application.dto.deliverypathdto.DeliveryPathCreateReq;
import com.sparta.delivery_service.application.dto.deliverypathdto.DeliveryPathRes;
import com.sparta.delivery_service.application.dto.deliverypathdto.DeliveryPathUpdateReq;
import com.sparta.delivery_service.application.service.DeliveryPathService;
import com.sparta.delivery_service.common.response.CommonResponse;
import com.sparta.delivery_service.domain.entity.DeliveryPath;
import jakarta.validation.Valid;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/delivery-paths")
@RequiredArgsConstructor
public class DeliveryPathController {

    private final DeliveryPathService deliveryPathService;

    /* 배송 경로 생성 */
    @PostMapping
    public ResponseEntity<CommonResponse<DeliveryPathRes>> createDeliveryPath(
        @RequestBody @Valid DeliveryPathCreateReq request) {
        DeliveryPathRes response = deliveryPathService.createDeliveryPath(request);
        return ResponseEntity.ok(CommonResponse.success(response));
    }

    /* 단일 배송 경로 조회 */
    @GetMapping("/{deliveryPathId}")
    public ResponseEntity<CommonResponse<DeliveryPathRes>> getDeliveryPath(
        @PathVariable UUID deliveryPathId) {
        DeliveryPathRes response = deliveryPathService.getDeliveryPath(deliveryPathId);
        return ResponseEntity.ok(CommonResponse.success(response));
    }

    /* 배송 경로 목록 조회 */
    @GetMapping
    public ResponseEntity<CommonResponse<Page<DeliveryPath>>> getAllDeliveryPaths(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size,
        @RequestParam(required = false) String sortBy
    ) {
        Pageable pageable = PageRequest.of(page, size,
            sortBy == null ? Sort.by("createdAt").descending() : Sort.by(sortBy));

        Page<DeliveryPath> response = deliveryPathService.getAllDeliveryPaths(pageable);
        return ResponseEntity.ok(CommonResponse.success(response));
    }

    /* 배송 경로 수정 */
    @PutMapping("/{deliveryPathId}")
    public ResponseEntity<CommonResponse<DeliveryPathRes>> updateDeliveryPath(
        @PathVariable UUID deliveryPathId,
        @RequestBody @Valid DeliveryPathUpdateReq request) {
        DeliveryPathRes response = deliveryPathService.updateDeliveryPath(deliveryPathId, request);
        return ResponseEntity.ok(CommonResponse.success(response));
    }

    @DeleteMapping("/{deliveryPathId}")
    public ResponseEntity<CommonResponse<Void>> deleteDeliveryPath(
        @PathVariable UUID deliveryPathId
    ) {
        deliveryPathService.deleteDeliveryPath(deliveryPathId);
        return ResponseEntity.ok(CommonResponse.success(null));
    }
}

