package com.example.order._event;

import lombok.*;

import java.io.*;
import java.util.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderCancelEvent implements Serializable {
    private UUID orderProductId;
    private Long amount;
}
