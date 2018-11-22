package com.example.java8.function;

import org.junit.Assert;
import org.junit.Test;

public class PrimitiveFunctionsTest {

    private static final String MESSAGE_SUM_RESULT = "Sum result not matched";
    private static final String MESSAGE_SUB_RESULT = "Sub result not matched";
    private static final String MESSAGE_DIV_RESULT = "Div result not matched";
    private static final String MESSAGE_MULT_RESULT = "Mult result not matched";

    @Test
    public void testSumInt() {
        int result = PrimitiveFunctions.sumInt.apply(5).apply(3);
        Assert.assertEquals(MESSAGE_SUM_RESULT, 8, result);
    }

    @Test
    public void testSubstractInt() {
        int result = PrimitiveFunctions.substractInt.apply(5).apply(3);
        Assert.assertEquals(MESSAGE_SUB_RESULT, 2, result);
    }

    @Test
    public void testDivideInt() {
        int result = PrimitiveFunctions.divideInt.apply(6).apply(3);
        Assert.assertEquals(MESSAGE_DIV_RESULT, 2, result);
    }

    @Test
    public void testMultiplyInt() {
        int result = PrimitiveFunctions.multiplyInt.apply(5).apply(3);
        Assert.assertEquals(MESSAGE_MULT_RESULT, 15, result);
    }


    @Test
    public void testSumLong() {
        long result = PrimitiveFunctions.sumLong.apply(5L).apply(3L);
        Assert.assertEquals(MESSAGE_SUM_RESULT, 8, result);
    }

    @Test
    public void testSubstractLong() {
        long result = PrimitiveFunctions.substractLong.apply(5L).apply(3L);
        Assert.assertEquals(MESSAGE_SUB_RESULT, 2, result);
    }

    @Test
    public void testDivideLong() {
        long result = PrimitiveFunctions.divideLong.apply(6L).apply(3L);
        Assert.assertEquals(MESSAGE_DIV_RESULT, 2, result);
    }

    @Test
    public void testMultiplyLong() {
        long result = PrimitiveFunctions.multiplyLong.apply(5L).apply(3L);
        Assert.assertEquals(MESSAGE_MULT_RESULT, 15, result);
    }

    @Test
    public void testExpInt() {
        int result = PrimitiveFunctions.exponentiationInt.apply(5);
        Assert.assertEquals(MESSAGE_MULT_RESULT, 25, result);
    }

    @Test
    public void testExpLong() {
        long result = PrimitiveFunctions.exponentiationLong.apply(5L);
        Assert.assertEquals(MESSAGE_MULT_RESULT, 25L, result);
    }


    @Test
    public void testComposeLong() {
        long result = PrimitiveFunctions.exponentiationLong.compose(PrimitiveFunctions.exponentiationLong).apply(2L);
        Assert.assertEquals(MESSAGE_MULT_RESULT, 16, result);
    }


    @Test
    public void testAndThenLong() {
        long result = PrimitiveFunctions.exponentiationLong.andThen(PrimitiveFunctions.exponentiationLong).apply(2L);
        Assert.assertEquals(MESSAGE_MULT_RESULT, 16, result);
    }


    @Test
    public void testComposeLong2() {
        // compose format
        long result = PrimitiveFunctions.exponentiationLong.compose(PrimitiveFunctions.divideLong.apply(6L)).apply(2L);

        Assert.assertEquals(MESSAGE_MULT_RESULT, 9, result);

        // step by step
        result = PrimitiveFunctions.divideLong.apply(6L).apply(2L);
        result = PrimitiveFunctions.exponentiationLong.apply(result);

        Assert.assertEquals(MESSAGE_MULT_RESULT, 9, result);
    }


    @Test
    public void testAndThenLong2() {
        // compose format
        long result = PrimitiveFunctions.exponentiationLong.andThen(PrimitiveFunctions.divideLong.apply(8L)).apply(2L);

        Assert.assertEquals(MESSAGE_MULT_RESULT, 2, result);

        // step by step

        result = PrimitiveFunctions.exponentiationLong.apply(2L);
        result = PrimitiveFunctions.divideLong.apply(8L).apply(result);

        Assert.assertEquals(MESSAGE_MULT_RESULT, 2, result);
    }

    @Test
    public void testComposeLong3() {
        // compose format
        long result = PrimitiveFunctions.exponentiationLong.compose(
                PrimitiveFunctions.divideLong.compose(
                        PrimitiveFunctions.substractLong.apply(7L)
                ).apply(3L)
        ).apply(2L);

        Assert.assertEquals(MESSAGE_MULT_RESULT, 4, result);

        // step by step
        result = PrimitiveFunctions.substractLong.apply(7L).apply(3L);
        result = PrimitiveFunctions.divideLong.apply(result).apply(2L);
        result = PrimitiveFunctions.exponentiationLong.apply(result);

        Assert.assertEquals(MESSAGE_MULT_RESULT, 4, result);
    }

    @Test
    public void testAndThenLong3() {
        // compose format
        Long result = PrimitiveFunctions.exponentiationLong.andThen(
                PrimitiveFunctions.sub5Long.andThen(
                        PrimitiveFunctions.add1Long
                )
        ).apply(2L);

        Assert.assertEquals(MESSAGE_MULT_RESULT, 0, result.longValue());

        // step by step

        result = PrimitiveFunctions.exponentiationLong.apply(2L);
        result = PrimitiveFunctions.sub5Long.apply(result);
        result = PrimitiveFunctions.add1Long.apply(result);

        Assert.assertEquals(MESSAGE_MULT_RESULT, 0, result.longValue());
    }


    @Test
    public void testComposeChain() {
        // compose format

        Long result = PrimitiveFunctions.composeChain(2L,
                PrimitiveFunctions.exponentiationLong,
                PrimitiveFunctions.add1Long,
                PrimitiveFunctions.sub5Long);

        Assert.assertEquals(MESSAGE_MULT_RESULT, 4, result.longValue());
    }

    @Test
    public void testAndThenChain() {
        // compose format

        Long result = PrimitiveFunctions.andThenChain(2L,
                PrimitiveFunctions.exponentiationLong,
                PrimitiveFunctions.add1Long,
                PrimitiveFunctions.sub5Long);

        Assert.assertEquals(MESSAGE_MULT_RESULT, 0, result.longValue());
    }

}
