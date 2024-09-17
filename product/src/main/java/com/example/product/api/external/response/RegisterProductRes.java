package com.example.product.api.external.response;

import lombok.*;

import java.io.*;
import java.util.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterProductRes implements Serializable {

    private UUID productId;

}
