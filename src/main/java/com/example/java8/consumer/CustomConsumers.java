package com.example.java8.consumer;

import java.util.function.Consumer;

import com.example.java8.CommonUtils;
import com.example.java8.exception.SampleException;
import com.example.java8.model.ResultStatus;
import com.example.java8.model.SampleContext;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomConsumers {

    private CustomConsumers() {
        // for sonar
    }

    /**
     *
     */
    private static Consumer<SampleContext> seperator = sampleContext -> log.info("=========================");

    /**
     *
     */
    public static final Consumer<SampleContext> before = sampleContext -> {
        seperator.accept(sampleContext);
        SampleContextConsumers.increaseOrderId.accept(sampleContext);
    };

    /**
     *
     */
    public static final Consumer<SampleContext> after = SampleContextConsumers.checkStatus;

    public static final Consumer<SampleContext> consumer1 = sampleContext -> {
        log.info(CommonUtils.LOG_METHOD_EXECUTED, "consumer1");
        sampleContext.setResultStatus(ResultStatus.SUCCESS);
    };

    /**
     *
     */
    public static final Consumer<SampleContext> consumer2 = sampleContext -> {
        log.info(CommonUtils.LOG_METHOD_EXECUTED, "consumer2");
        sampleContext.setResultStatus(ResultStatus.SUCCESS);
    };

    /**
     *
     */
    public static final Consumer<SampleContext> consumer3 = sampleContext -> {
        log.info(CommonUtils.LOG_METHOD_EXECUTED, "consumer2");
        sampleContext.setResultStatus(ResultStatus.SUCCESS);
    };

    /**
     *
     */
    public static final Consumer<SampleContext> consumer4 = sampleContext -> {
        log.info(CommonUtils.LOG_METHOD_EXECUTED, "consumer4");
        sampleContext.setResultStatus(ResultStatus.SUCCESS);
    };

    /**
     *
     */
    public static final Consumer<SampleContext> consumer5 = CustomConsumers::execute5;

    /**
     *
     */
    private static void execute5(SampleContext sampleContext) {
        log.info(CommonUtils.LOG_METHOD_EXECUTED, "consumer5");
        sampleContext.setResultStatus(ResultStatus.SUCCESS);
    }

    /**
     *
     */
    public static final Consumer<SampleContext> consumerSetFail = sampleContext -> {
        log.info(CommonUtils.LOG_METHOD_EXECUTED, "consumerSetFail");
        sampleContext.setResultStatus(ResultStatus.FAIL);
    };

    /**
     *
     */
    public static final Consumer<SampleContext> consumerThrowException = sampleContext -> {
        log.info(CommonUtils.LOG_METHOD_EXECUTED, "consumerThrowException");
        throw new SampleException("Dummy Exception");
    };


    /**
     *
     */
    public static Consumer<SampleContext> execute(Consumer<SampleContext> consumer) {
        return before.andThen(consumer).andThen(after);
    }
}
