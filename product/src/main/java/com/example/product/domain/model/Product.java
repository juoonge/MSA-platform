package com.example.product.domain.model;

import com.example.product._common.*;
import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Entity
@Table(name = "p_products")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "product_id")
    private UUID id;
    private String name;
    private Long stock;
    private UUID producerVendorId;
    private UUID belongingHubId;
    @Version
    private Long version;

    @Builder
    public Product(String name, Long stock, UUID producerVendorId, UUID belongingHubId) {
        this.name = name;
        this.stock = stock;
        this.producerVendorId = producerVendorId;
        this.belongingHubId = belongingHubId;
    }

    public Long decreaseStock(Long amount) {
        this.stock = this.stock - amount;
        return this.stock;
    }

    public Long increaseStock(Long amount) {
        this.stock = this.stock + amount;
        return this.stock;
    }

    public void remove() {
        super.delete();
    }
}
