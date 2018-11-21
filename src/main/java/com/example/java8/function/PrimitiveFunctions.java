package com.example.java8.function;

import java.util.function.Function;

import org.apache.commons.lang3.ArrayUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PrimitiveFunctions {

    private static final String LOG_SUM_RESULT = "Sum: {}+{}";
    private static final String LOG_SUB_RESULT = "Sub: {}-{}";
    private static final String LOG_DIV_RESULT = "Div: {}/{}";
    private static final String LOG_MULT_RESULT = "Mult: {}*{}";

    static Function<Integer, Function<Integer, Integer>> sumInt = i -> j -> {
        log.info(LOG_SUM_RESULT, i, j);
        return i + j;
    };
    static Function<Integer, Function<Integer, Integer>> substractInt = i -> j -> {
        log.info(LOG_SUB_RESULT, i, j);
        return i - j;
    };
    static Function<Integer, Function<Integer, Integer>> divideInt = i -> j -> {
        log.info(LOG_DIV_RESULT, i, j);
        return i / j;
    };
    static Function<Integer, Function<Integer, Integer>> multiplyInt = i -> j -> {
        log.info(LOG_MULT_RESULT, i, j);
        return i * j;
    };

    static Function<Long, Function<Long, Long>> sumLong = i -> j -> {
        log.info(LOG_SUM_RESULT, i, j);
        return i + j;
    };
    static Function<Long, Function<Long, Long>> substractLong = i -> j -> {
        log.info(LOG_SUB_RESULT, i, j);
        return i - j;
    };
    static Function<Long, Function<Long, Long>> divideLong = i -> j -> {
        log.info(LOG_DIV_RESULT, i, j);
        return i / j;
    };
    static Function<Long, Function<Long, Long>> multiplyLong = i -> j -> {
        log.info(LOG_MULT_RESULT, i, j);
        return i * j;
    };

    static Function<Integer, Integer> exponentiationInt = i -> multiplyInt.apply(i).apply(i);
    static Function<Long, Long> exponentiationLong = i -> multiplyLong.apply(i).apply(i);

    static Function<Long, Long> add1Long = i -> {
        log.info(LOG_SUM_RESULT, i, 1);
        return i + 1;
    };

    static Function<Long, Long> sub5Long = i -> {
        log.info(LOG_SUB_RESULT, i, 5);
        return i - 5;
    };


    public static <T, V> V composeChain(T t, Function<T, V>... functions) {

        if (ArrayUtils.isEmpty(functions)) {
            return null;
        }

        Function function = functions[0];
        for (int i = 1; i < functions.length; i++) {
            function = function.compose(functions[i]);
        }

        return (V) function.apply(t);
    }

}
