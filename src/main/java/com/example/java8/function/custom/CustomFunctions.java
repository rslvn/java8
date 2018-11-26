package com.example.java8.function.custom;

import lombok.extern.slf4j.Slf4j;

import java.util.function.Function;

import com.example.java8.consumer.CustomConsumers;
import com.example.java8.model.SampleContext;

@Slf4j
public class CustomFunctions {
    private CustomFunctions() {
        // for sonar
    }

    /**
     *
     */
    private static Function<SampleContext, SampleContext> beforeFunc = sampleContext -> {
        CustomConsumers.before.accept(sampleContext);
        return sampleContext;
    };
    /**
     *
     */
    private static Function<SampleContext, SampleContext> afterFunc = sampleContext -> {
        CustomConsumers.after.accept(sampleContext);
        return sampleContext;
    };

    /**
     * @param function
     * @return
     */
    static Function<SampleContext, SampleContext> execute(Function<SampleContext, SampleContext> function) {
        return beforeFunc.andThen(function).andThen(afterFunc);
    }

    /**
     *
     */
    static Function<SampleContext, SampleContext> func1 = sampleContext -> {
        CustomConsumers.consumer1.accept(sampleContext);
        return sampleContext;
    };

    /**
     *
     */
    static Function<SampleContext, SampleContext> func2 = sampleContext -> {
        CustomConsumers.consumer2.accept(sampleContext);
        return sampleContext;
    };

    /**
     *
     */
    static Function<SampleContext, SampleContext> func3 = sampleContext -> {
        CustomConsumers.consumer3.accept(sampleContext);
        return sampleContext;
    };

    /**
     *
     */
    static Function<SampleContext, SampleContext> func4 = sampleContext -> {
        CustomConsumers.consumer4.accept(sampleContext);
        return sampleContext;
    };

    /**
     *
     */
    static Function<SampleContext, SampleContext> func5 = CustomFunctions::execute5;

    /**
     * @param sampleContext
     * @return
     */
    private static SampleContext execute5(SampleContext sampleContext) {
        CustomConsumers.consumer5.accept(sampleContext);
        return sampleContext;
    }

    /**
     * This function calls CustomConsumers.consumerSetFail to set FAIL the result status of a SampleContext
     * This is created for testing 'after function' process
     */
    static Function<SampleContext, SampleContext> funcSetFail = sampleContext -> {
        CustomConsumers.consumerSetFail.accept(sampleContext);
        return sampleContext;
    };

    /**
     * This function calls CustomConsumers.consumerThrowException to throw an exception to test
     * what happens when an exception is thrown in a function/composer chain
     */
    static Function<SampleContext, SampleContext> funcThrowException = sampleContext -> {
        CustomConsumers.consumerThrowException.accept(sampleContext);
        return sampleContext;
    };
}
