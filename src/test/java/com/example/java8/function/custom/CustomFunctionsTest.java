package com.example.java8.function.custom;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import static com.example.java8.function.custom.CustomFunctions.execute;

import com.example.java8.model.SampleContext;

@Slf4j
public class CustomFunctionsTest {

    private final String resultLogFormat = "Final context: {}";

    @Test
    public void testNoBeforeAfterFunc() {
        SampleContext context = SampleContext.builder().build();
        CustomFunctions.func1
                .andThen(CustomFunctions.func2)
                .andThen(CustomFunctions.func3)
                .andThen(CustomFunctions.func4)
                .andThen(CustomFunctions.func5)
                .apply(context);

        log.info(resultLogFormat,context);
    }

    @Test
    public void testBeforeAfterFunc() {
        SampleContext context = SampleContext.builder().build();
        execute(CustomFunctions.func1)
                .andThen(execute(CustomFunctions.func2))
                .andThen(execute(CustomFunctions.func3))
                .andThen(execute(CustomFunctions.func4))
                .andThen(execute(CustomFunctions.func5))
                .apply(context);

        log.info(resultLogFormat,context);
    }

    @Test(expected = RuntimeException.class)
    public void testBeforeAfterFuncStatusFailed() {
        SampleContext context = SampleContext.builder().build();

        execute(CustomFunctions.func1)
                .andThen(execute(CustomFunctions.func2))
                .andThen(execute(CustomFunctions.func3))
                .andThen(execute(CustomFunctions.funcSetFail))
                .andThen(execute(CustomFunctions.func5))
                .apply(context);

        log.info(resultLogFormat,context);
    }

    @Test(expected = RuntimeException.class)
    public void testBeforeAfterFuncException() {
        SampleContext context = SampleContext.builder().build();

        execute(CustomFunctions.func1)
                .andThen(execute(CustomFunctions.func2))
                .andThen(execute(CustomFunctions.func3))
                .andThen(execute(CustomFunctions.funcThrowException))
                .andThen(execute(CustomFunctions.func5))
                .apply(context);

        log.info(resultLogFormat,context);
    }
}
