package com.sparta.auth.application.dto;

import com.sparta.auth.domain.model.DeliveryManagerRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryManagerUpdateRequest {
    private DeliveryManagerRole role;
}
