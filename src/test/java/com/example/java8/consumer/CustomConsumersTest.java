package com.example.java8.consumer;

import static com.example.java8.consumer.CustomConsumers.execute;
import org.junit.Test;

import com.example.java8.model.SampleContext;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomConsumersTest {

    private final String resultLogFormat = "Final context: {}";

    @Test
    public void testNoBeforeAfter() {
        SampleContext context = SampleContext.builder().build();
        CustomConsumers.consumer1
                .andThen(CustomConsumers.consumer2)
                .andThen(CustomConsumers.consumer3)
                .andThen(CustomConsumers.consumer4)
                .andThen(CustomConsumers.consumer5)
                .accept(context);

        log.info(resultLogFormat,context);
    }

    @Test
    public void testBeforeAfter() {
        SampleContext context = SampleContext.builder().build();
        execute(CustomConsumers.consumer1)
                .andThen(execute(CustomConsumers.consumer2))
                .andThen(execute(CustomConsumers.consumer3))
                .andThen(execute(CustomConsumers.consumer4))
                .andThen(execute(CustomConsumers.consumer5))
                .accept(context);

        log.info(resultLogFormat,context);
    }

    @Test(expected = RuntimeException.class)
    public void testBeforeAfterStatusFailed() {
        SampleContext context = SampleContext.builder().build();
        execute(CustomConsumers.consumer1)
                .andThen(execute(CustomConsumers.consumer2))
                .andThen(execute(CustomConsumers.consumer3))
                .andThen(execute(CustomConsumers.consumerSetFail))
                .andThen(execute(CustomConsumers.consumer5))
                .accept(context);

        log.info(resultLogFormat,context);
    }

    @Test(expected = RuntimeException.class)
    public void testBeforeAfterException() {
        SampleContext context = SampleContext.builder().build();
        execute(CustomConsumers.consumer1)
                .andThen(execute(CustomConsumers.consumer2))
                .andThen(execute(CustomConsumers.consumer3))
                .andThen(execute(CustomConsumers.consumerThrowException))
                .andThen(execute(CustomConsumers.consumer5))
                .accept(context);

        log.info(resultLogFormat,context);
    }

}
