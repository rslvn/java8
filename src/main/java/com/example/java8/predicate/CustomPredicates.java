package com.example.java8.predicate;

import java.util.function.Predicate;

import com.example.java8.AppUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomPredicates {

    private CustomPredicates() {
        // for sonar
    }

    public static final Predicate<Number> isZero = number -> {
        log.info(AppUtils.LOG_METHOD_EXECUTED, "isPositive");
        return CustomPredicates.isNonNull.test(number) && number.longValue() == 0;
    };

    public static final Predicate<Number> isPositive = number -> {
        log.info(AppUtils.LOG_METHOD_EXECUTED, "isPositive");
        return CustomPredicates.isNonNull.test(number) && number.longValue() > 0;
    };

    public static final Predicate<Number> isNegative = number -> {
        log.info(AppUtils.LOG_METHOD_EXECUTED, "isNegative");
        return CustomPredicates.isNonNull.test(number) && number.longValue() < 0;
    };

    public static final Predicate<? super Object> isNull = obj -> {
        log.info(AppUtils.LOG_METHOD_EXECUTED, "isNull");
        return obj == null;
    };

    public static final Predicate<? super Object> isNonNull = obj -> {
        log.info(AppUtils.LOG_METHOD_EXECUTED, "isNonNull");
        return obj != null;
    };

}
