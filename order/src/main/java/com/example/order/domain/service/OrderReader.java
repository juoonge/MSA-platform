package com.example.order.domain.service;

import com.example.order.app.dto.*;
import com.example.order.domain.model.*;
import org.springframework.data.domain.*;

import java.util.*;

import static com.example.order.app.dto.OrderDto.*;

public interface OrderReader {
    Order getOrder(UUID orderId);

    List<Order> searchOrder(Pageable page);
}
