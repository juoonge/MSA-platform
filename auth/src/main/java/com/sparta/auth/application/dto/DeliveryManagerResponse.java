package com.sparta.auth.application.dto;

import com.sparta.auth.domain.model.DeliveryManager;
import com.sparta.auth.domain.model.DeliveryManagerRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryManagerResponse{
    private UUID id;
    private DeliveryManagerRole role;
    private UUID user_id;

    public static DeliveryManagerResponse fromEntity(DeliveryManager manager){
        return new DeliveryManagerResponse(
                manager.getId(),
                manager.getRole(),
                manager.getUser_id().getId()
        );
    }
}
