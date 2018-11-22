package com.example.java8.function.custom;

import lombok.extern.slf4j.Slf4j;

import java.util.function.Consumer;
import java.util.function.Function;

@Slf4j
public class CustomFunctions {

    private static final String LOG_TEXT = "{} executed";

    private static Consumer<FunctionContext> seperator = functionContext -> {
        log.info("=========================");
    };

    private static Consumer<FunctionContext> setOrderId = functionContext -> {
        log.info(LOG_TEXT, "setOrderId");
        functionContext.setId(functionContext.getId() + 1);
    };

    private static Consumer<FunctionContext> checkStatus = functionContext -> {
        log.info(LOG_TEXT, "checkStatus");
        if (ResultStatus.FAIL.equals(functionContext.getResultStatus())) {
            throw new RuntimeException("Status Failed");
        }
    };

    private static Function<FunctionContext, FunctionContext> beforeFunc = functionContext -> {
        seperator.accept(functionContext);
        setOrderId.accept(functionContext);
        return functionContext;
    };

    private static Function<FunctionContext, FunctionContext> afterFunc = functionContext -> {
        checkStatus.accept(functionContext);
        return functionContext;
    };

    static Function<FunctionContext, FunctionContext> execute(Function<FunctionContext, FunctionContext> function) {
        return beforeFunc.andThen(function).andThen(afterFunc);
    }

    static Function<FunctionContext, FunctionContext> func1 = functionContext -> {
        log.info(LOG_TEXT, "func1");
        functionContext.setResultStatus(ResultStatus.SUCCESS);
        return functionContext;
    };

    static Function<FunctionContext, FunctionContext> func2 = functionContext -> {
        log.info(LOG_TEXT, "func2");
        functionContext.setResultStatus(ResultStatus.SUCCESS);
        return functionContext;
    };

    static Function<FunctionContext, FunctionContext> func3 = functionContext -> {
        log.info(LOG_TEXT, "func2");
        functionContext.setResultStatus(ResultStatus.SUCCESS);
        return functionContext;
    };

    static Function<FunctionContext, FunctionContext> func4 = functionContext -> {
        log.info(LOG_TEXT, "func4");
        functionContext.setResultStatus(ResultStatus.SUCCESS);
        return functionContext;
    };

    static Function<FunctionContext, FunctionContext> func5 = CustomFunctions::execute5;

    static private FunctionContext execute5(FunctionContext functionContext) {
        log.info(LOG_TEXT, "func5");
        functionContext.setResultStatus(ResultStatus.SUCCESS);
        return functionContext;
    }

    static Function<FunctionContext, FunctionContext> funcSetFail = functionContext -> {
        log.info(LOG_TEXT, "funcSetFail");
        functionContext.setResultStatus(ResultStatus.FAIL);
        return functionContext;
    };

    static Function<FunctionContext, FunctionContext> funcThrowException = functionContext -> {
        log.info(LOG_TEXT, "funcThrowException");
        throw new RuntimeException("Dummy Exception");
    };

    public enum ResultStatus {
        SUCCESS, FAIL
    }
}
