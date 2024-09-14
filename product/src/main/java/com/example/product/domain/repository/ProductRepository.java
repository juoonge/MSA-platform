package com.example.product.domain.repository;

import com.example.product.domain.model.*;
import org.springframework.data.jpa.repository.*;

import java.util.*;

public interface ProductRepository extends JpaRepository<Product, UUID> {
}
