package com.example.java8.consumer;

import java.util.function.Consumer;

import com.example.java8.CommonUtils;
import com.example.java8.exception.SampleException;
import com.example.java8.function.PrimitiveFunctions;
import com.example.java8.model.SampleContext;
import com.example.java8.predicate.SampleContextPredicates;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SampleContextConsumers {

    private SampleContextConsumers() {
        // for sonar
    }

    /**
     *
     */
    public static final Consumer<SampleContext> checkStatus = sampleContext -> {
        log.info(CommonUtils.LOG_METHOD_EXECUTED, "checkStatus");
        if (SampleContextPredicates.fail().test(sampleContext)) {
            throw new SampleException("Status Failed");
        }
    };

    /**
     *
     */
    public static final Consumer<SampleContext> increaseOrderId = sampleContext -> {
        log.info(CommonUtils.LOG_METHOD_EXECUTED, "increaseOrderId");
        sampleContext.setOrder(
                PrimitiveFunctions.add1Long.apply(sampleContext.getOrder())
        );
    };

    /**
     *
     */
    public static final Consumer<SampleContext> printOrderAndName() {
        return sampleContext -> log.info("Order: {}, Name: {}", sampleContext.getOrder(), sampleContext.getName());
    }
}
