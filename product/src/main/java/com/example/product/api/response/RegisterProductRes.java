package com.example.product.api.response;

import com.example.product.app.dto.*;
import com.example.product.app.dto.ProductDto.*;
import lombok.*;

import java.io.*;
import java.util.*;

@Getter
@AllArgsConstructor
public class RegisterProductRes implements Serializable {

    private UUID productId;

    public static RegisterProductRes of(ProductInfo productInfo) {
        return new RegisterProductRes(productInfo.getId());
    }
}
