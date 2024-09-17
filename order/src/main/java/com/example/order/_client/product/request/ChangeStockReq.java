package com.example.order._client.product.request;

import lombok.*;

import java.io.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChangeStockReq implements Serializable {
    private Long amount;
}
