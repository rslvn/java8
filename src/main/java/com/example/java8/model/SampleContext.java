package com.example.java8.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SampleContext {
    private String name;
    private long order;
    private Long previousOrderId;
    private ResultStatus resultStatus;
}
