package com.sparta.delivery_service.presentation;

import com.sparta.delivery_service.application.dto.deliverydto.DeliveryAddressDto;
import com.sparta.delivery_service.application.dto.deliverydto.DeliveryAddressUpdateRequest;
import com.sparta.delivery_service.application.dto.deliverydto.DeliveryDTO;
import com.sparta.delivery_service.application.dto.deliverydto.DeliveryRes;
import com.sparta.delivery_service.application.dto.deliverydto.DeliveryStatusDto;
import com.sparta.delivery_service.application.dto.deliverydto.DeliveryStatusUpdateRequest;
import com.sparta.delivery_service.application.dto.deliverydto.DeliveryUpdateRequest;
import com.sparta.delivery_service.application.mapper.DeliveryMapper;
import com.sparta.delivery_service.application.service.DeliveryService;
import com.sparta.delivery_service.common.response.CommonResponse;
import com.sparta.delivery_service.domain.entity.Delivery;
import jakarta.validation.Valid;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/deliveries")
@RequiredArgsConstructor
public class DeliveryController {

    private final DeliveryService deliveryService;
    private final DeliveryMapper deliveryMapper;

    @PostMapping
    public ResponseEntity<CommonResponse<DeliveryRes>> createDelivery(
        @RequestBody @Valid DeliveryDTO deliveryDto) {
        Delivery delivery = deliveryService.createDelivery(deliveryDto);
        return new ResponseEntity<>(CommonResponse.success(DeliveryRes.of(delivery)),
            HttpStatus.CREATED);
    }

    @GetMapping("/{deliveryId}")
    public ResponseEntity<CommonResponse<DeliveryRes>> getDelivery(@PathVariable UUID deliveryId) {
        Delivery delivery = deliveryService.getDeliveryById(deliveryId);
        return new ResponseEntity<>(CommonResponse.success(DeliveryRes.of(delivery)),
            HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<CommonResponse<Page<Delivery>>> getAllDeliveries(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size,
        @RequestParam(required = false) String sortBy
    ) {
        Pageable pageable = PageRequest.of(page, size,
            sortBy == null ? Sort.by("createdAt").descending() : Sort.by(sortBy));

        Page<Delivery> deliveries = deliveryService.getAllDeliveries(pageable);

        return ResponseEntity.ok(CommonResponse.success(deliveries));
    }

    @PutMapping("/{delivery_id}")
    public ResponseEntity<CommonResponse<DeliveryDTO>> updateDelivery(
        @PathVariable UUID delivery_id,
        @RequestBody DeliveryUpdateRequest request
        //,@RequestHeader("Authorization") String token
    ) {

        DeliveryDTO updatedDelivery = deliveryService.updateDelivery(delivery_id, request);
        return ResponseEntity.ok(CommonResponse.success(updatedDelivery));
    }

    @PatchMapping("/{delivery_id}/delivery-status")
    public ResponseEntity<CommonResponse<DeliveryStatusDto>> updateDeliveryStatus(
        @PathVariable UUID delivery_id,
        @RequestBody DeliveryStatusUpdateRequest request
        //,@RequestHeader("Authorization") String token
    ) {

        DeliveryStatusDto updatedStatus = deliveryService.updateDeliveryStatus(delivery_id,
            request.getDeliveryStatus());
        return ResponseEntity.ok(CommonResponse.success(updatedStatus));
    }

    @PatchMapping("/{delivery_id}/delivery-address")
    public ResponseEntity<CommonResponse<DeliveryAddressDto>> updateDeliveryAddress(
        @PathVariable UUID delivery_id,
        @RequestBody DeliveryAddressUpdateRequest request
        //,@RequestHeader("Authorization") String token
    ) {

        DeliveryAddressDto updatedAddress = deliveryService.updateDeliveryAddress(delivery_id,
            request.getAddress());
        return ResponseEntity.ok(CommonResponse.success(updatedAddress));
    }

    @DeleteMapping("/internal/{delivery_id}")
    public DeliveryDTO deleteDelivery(
        @PathVariable UUID delivery_id
        //,@RequestHeader("Authorization") String token
    ) {

        Delivery delivery = deliveryService.deleteDelivery(delivery_id);
        return deliveryMapper.toDto(delivery);
    }

}
