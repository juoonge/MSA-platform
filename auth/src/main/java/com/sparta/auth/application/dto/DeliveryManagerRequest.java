package com.sparta.auth.application.dto;

import com.sparta.auth.domain.model.DeliveryManagerRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryManagerRequest {
    private DeliveryManagerRole role;
    private UUID user_id;
}
