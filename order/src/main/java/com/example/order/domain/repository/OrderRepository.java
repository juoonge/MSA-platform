package com.example.order.domain.repository;

import com.example.order.domain.model.*;
import org.springframework.data.jpa.repository.*;

import java.util.*;

public interface OrderRepository extends JpaRepository<Order, UUID> {
}
