package com.example.order.domain.service;

import com.example.order.domain.model.*;
import org.springframework.data.domain.*;

import java.util.*;

public interface OrderReader {
    Order getOrder(UUID orderId);

    Order getExistOrder(UUID orderId);

    List<Order> searchOrder(Pageable page);

    List<Order> searchExistOrder(Pageable page);
}
