package com.example.order.api.response;

import lombok.*;

import java.io.*;
import java.util.*;

@Getter
@AllArgsConstructor
public class RegisterOrderRes implements Serializable {
    private UUID orderId;
}
