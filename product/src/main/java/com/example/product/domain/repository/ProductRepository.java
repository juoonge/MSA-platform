package com.example.product.domain.repository;

import com.example.product.domain.model.*;
import jakarta.persistence.*;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.*;

import java.util.*;

public interface ProductRepository extends JpaRepository<Product, UUID> {
    Optional<Product> findByIdAndIsDeleted(UUID productId, boolean b);

    Page<Product> findAllByIsDeleted(Pageable page, boolean b);

    @Lock(LockModeType.OPTIMISTIC)
    @Query("select p from Product p where p.id = :productId and p.isDeleted = false")
    Optional<Product> findByIdWithOptimisticLock(UUID productId);
}
