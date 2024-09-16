package com.example.order._event;

import lombok.*;

import java.io.*;
import java.util.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CancelOrderEvent implements Serializable {
    private UUID orderId;
}
