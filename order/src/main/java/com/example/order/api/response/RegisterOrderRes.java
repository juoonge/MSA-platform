package com.example.order.api.response;

import lombok.*;

import java.io.*;
import java.util.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterOrderRes implements Serializable {
    private UUID orderId;
}
