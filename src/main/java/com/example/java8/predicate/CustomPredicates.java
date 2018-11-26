package com.example.java8.predicate;

import java.util.Objects;
import java.util.function.Predicate;

import com.example.java8.CommonUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomPredicates {

    private CustomPredicates() {
        // for sonar
    }

    public static final Predicate<Number> isZero = number -> {
        log.info(CommonUtils.LOG_METHOD_EXECUTED, "isPositive");
        return  isNonNull().test(number) && number.longValue() == 0;
    };

    public static final Predicate<Number> isPositive = number -> {
        log.info(CommonUtils.LOG_METHOD_EXECUTED, "isPositive");
        return isNonNull().test(number) && number.longValue() > 0;
    };

    public static final Predicate<Number> isNegative = number -> {
        log.info(CommonUtils.LOG_METHOD_EXECUTED, "isNegative");
        return isNonNull().test(number) && number.longValue() < 0;
    };

    public static <T> Predicate<T> isNull() {
        log.info(CommonUtils.LOG_METHOD_EXECUTED, "isNull");
        return Objects::isNull;
    }

    public static <T> Predicate<T> isNonNull() {
        log.info(CommonUtils.LOG_METHOD_EXECUTED, "isNonNull");
        return Objects::nonNull;
    }
}
