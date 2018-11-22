package com.example.java8.consumer;

import java.util.function.Consumer;

import com.example.java8.exception.SampleException;
import com.example.java8.model.ResultStatus;
import com.example.java8.model.SampleContext;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomConsumers {

    private CustomConsumers() {
        // for sonar
    }


    private static final String LOG_TEXT = "{} executed";

    /**
     *
     */
    private static Consumer<SampleContext> seperator = sampleContext -> log.info("=========================");

    /**
     *
     */
    private static Consumer<SampleContext> setOrderId = sampleContext -> {
        log.info(LOG_TEXT, "setOrderId");
        sampleContext.setId(sampleContext.getId() + 1);
    };

    /**
     *
     */
    private static Consumer<SampleContext> checkStatus = sampleContext -> {
        log.info(LOG_TEXT, "checkStatus");
        if (ResultStatus.FAIL.equals(sampleContext.getResultStatus())) {
            throw new SampleException("Status Failed");
        }
    };

    /**
     *
     */
    public static Consumer<SampleContext> before = sampleContext -> {
        seperator.accept(sampleContext);
        setOrderId.accept(sampleContext);
    };

    /**
     *
     */
    public static Consumer<SampleContext> after = sampleContext -> checkStatus.accept(sampleContext);

    /**
     *
     */
    static Consumer<SampleContext> execute(Consumer<SampleContext> consumer) {
        return before.andThen(consumer).andThen(after);
    }

    public static Consumer<SampleContext> consumer1 = sampleContext -> {
        log.info(LOG_TEXT, "consumer1");
        sampleContext.setResultStatus(ResultStatus.SUCCESS);
    };

    /**
     *
     */
    public static Consumer<SampleContext> consumer2 = sampleContext -> {
        log.info(LOG_TEXT, "consumer2");
        sampleContext.setResultStatus(ResultStatus.SUCCESS);
    };

    /**
     *
     */
    public static Consumer<SampleContext> consumer3 = sampleContext -> {
        log.info(LOG_TEXT, "consumer2");
        sampleContext.setResultStatus(ResultStatus.SUCCESS);
    };

    /**
     *
     */
    public static Consumer<SampleContext> consumer4 = sampleContext -> {
        log.info(LOG_TEXT, "consumer4");
        sampleContext.setResultStatus(ResultStatus.SUCCESS);
    };

    /**
     *
     */
    public static Consumer<SampleContext> consumer5 = CustomConsumers::execute5;

    /**
     *
     */
    private static void execute5(SampleContext sampleContext) {
        log.info(LOG_TEXT, "consumer5");
        sampleContext.setResultStatus(ResultStatus.SUCCESS);
    }

    /**
     *
     */
    public static Consumer<SampleContext> consumerSetFail = sampleContext -> {
        log.info(LOG_TEXT, "consumerSetFail");
        sampleContext.setResultStatus(ResultStatus.FAIL);
    };

    /**
     *
     */
    public static Consumer<SampleContext> consumerThrowException = sampleContext -> {
        log.info(LOG_TEXT, "consumerThrowException");
        throw new SampleException("Dummy Exception");
    };

}
