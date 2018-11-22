package com.example.java8.functional;

import lombok.extern.slf4j.Slf4j;

import java.util.function.*;

@Slf4j
public class PrimitiveFunctionalInterfaces {

    private static final String LOG_SUM_RESULT = "Sum: {}+{}";
    private static final String LOG_SUB_RESULT = "Sub: {}-{}";
    private static final String LOG_DIV_RESULT = "Div: {}/{}";
    private static final String LOG_MULT_RESULT = "Mult: {}*{}";

    static IntBinaryOperator sumInt = (i, j) -> {
        log.info(LOG_SUM_RESULT, i, j);
        return i + j;
    };
    static IntBinaryOperator substractInt = (i, j) -> {
        log.info(LOG_SUB_RESULT, i, j);
        return i - j;
    };
    static IntBinaryOperator divideInt = (i, j) -> {
        log.info(LOG_DIV_RESULT, i, j);
        return i / j;
    };
    static IntBinaryOperator multiplyInt = (i, j) -> i * j;

    static LongBinaryOperator sumLong = (i, j) -> {
        log.info(LOG_SUM_RESULT, i, j);
        return i + j;
    };
    static LongBinaryOperator substractLong = (i, j) -> {
        log.info(LOG_SUB_RESULT, i, j);
        return i - j;
    };
    static LongBinaryOperator divideLong = (i, j) -> {
        log.info(LOG_DIV_RESULT, i, j);
        return i / j;
    };
    static LongBinaryOperator multiplyLong = (i, j) -> {
        log.info(LOG_MULT_RESULT, i, j);
        return i * j;
    };

    static LongUnaryOperator add1Long = i -> {
        log.info(LOG_SUM_RESULT, i, 1);
        return i + 1;
    };

    static LongUnaryOperator sub5Long = i -> {
        log.info(LOG_SUB_RESULT, i, 5);
        return i - 5;
    };

    static IntUnaryOperator exponentiationInt = i -> multiplyInt.applyAsInt(i, i);
    static LongUnaryOperator exponentiationLong = i -> multiplyLong.applyAsLong(i, i);

}
