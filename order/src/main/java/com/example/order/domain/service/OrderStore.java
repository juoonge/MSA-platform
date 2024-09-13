package com.example.order.domain.service;

import com.example.order.domain.model.*;

public interface OrderStore {
    Order store(Order initOrder);
}
