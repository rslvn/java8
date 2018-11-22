package com.example.java8.function.custom;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FunctionContext {
    private String name;
    private long id;
    private Long previousFuctionId;
    private CustomFunctions.ResultStatus resultStatus;
}
