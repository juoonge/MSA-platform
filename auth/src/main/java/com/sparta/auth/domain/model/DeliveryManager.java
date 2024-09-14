package com.sparta.auth.domain.model;

import com.sparta.auth.application.dto.DeliveryManagerRequest;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.UUID;

@Entity(name="p_delivery_managers")
@Getter
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@NoArgsConstructor(access= AccessLevel.PROTECTED)
@Builder
public class DeliveryManager extends BaseEntity{
    @Id
    @GeneratedValue(strategy= GenerationType.UUID)
    @Column(name="delivery_manager_id")
    private UUID id;

    @Column(name="delivery_manager_role")
    private DeliveryManagerRole role;

    @OneToOne
    @JoinColumn(name="user_id",unique=true)
    private User user_id;

//    @OneToOne
//    @JoinColumn(name="hub_id",unique=true)
//    private Hub hub_id;

    public static DeliveryManager create(DeliveryManagerRequest request,User user){
        return DeliveryManager.builder()
                .role(request.getRole())
                .user_id(user)
                .build();
    }

    public void update(DeliveryManagerRole role){
        this.role=role;
    }
}
