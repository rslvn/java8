package com.example.java8.functional;

import org.junit.Assert;
import org.junit.Test;

public class PrimitiveFunctionalInterfacesTest {

    private static final String MESSAGE_SUM_RESULT = "Sum result not matched";
    private static final String MESSAGE_SUB_RESULT = "Sub result not matched";
    private static final String MESSAGE_DIV_RESULT = "Div result not matched";
    private static final String MESSAGE_MULT_RESULT = "Mult result not matched";

    @Test
    public void testSumInt() {
        int result = PrimitiveFunctionalInterfaces.sumInt.applyAsInt(5, 3);
        Assert.assertEquals(MESSAGE_SUM_RESULT, 8, result);
    }

    @Test
    public void testSubstractInt() {
        int result = PrimitiveFunctionalInterfaces.substractInt.applyAsInt(5, 3);
        Assert.assertEquals(MESSAGE_SUB_RESULT, 2, result);
    }

    @Test
    public void testDivideInt() {
        int result = PrimitiveFunctionalInterfaces.divideInt.applyAsInt(6, 3);
        Assert.assertEquals(MESSAGE_DIV_RESULT, 2, result);
    }

    @Test
    public void testMultiplyInt() {
        int result = PrimitiveFunctionalInterfaces.multiplyInt.applyAsInt(5, 3);
        Assert.assertEquals(MESSAGE_MULT_RESULT, 15, result);
    }


    @Test
    public void testSumLong() {
        long result = PrimitiveFunctionalInterfaces.sumLong.applyAsLong(5, 3);
        Assert.assertEquals(MESSAGE_SUM_RESULT, 8, result);
    }

    @Test
    public void testSubstractLong() {
        long result = PrimitiveFunctionalInterfaces.substractLong.applyAsLong(5, 3);
        Assert.assertEquals(MESSAGE_SUB_RESULT, 2, result);
    }

    @Test
    public void testDivideLong() {
        long result = PrimitiveFunctionalInterfaces.divideLong.applyAsLong(6, 3);
        Assert.assertEquals(MESSAGE_DIV_RESULT, 2, result);
    }

    @Test
    public void testMultiplyLong() {
        long result = PrimitiveFunctionalInterfaces.multiplyLong.applyAsLong(5, 3);
        Assert.assertEquals(MESSAGE_MULT_RESULT, 15, result);
    }

    @Test
    public void testExpInt() {
        int result = PrimitiveFunctionalInterfaces.exponentiationInt.applyAsInt(5);
        Assert.assertEquals(MESSAGE_MULT_RESULT, 25, result);
    }

    @Test
    public void testExpLong() {
        long result = PrimitiveFunctionalInterfaces.exponentiationLong.applyAsLong(5L);
        Assert.assertEquals(MESSAGE_MULT_RESULT, 25L, result);
    }

    @Test
    public void testComposeLong() {
        long result = PrimitiveFunctionalInterfaces.exponentiationLong.compose(PrimitiveFunctionalInterfaces.exponentiationLong).applyAsLong(2L);
        Assert.assertEquals(MESSAGE_MULT_RESULT, 16, result);
    }

    @Test
    public void testAndThenLong() {
        long result = PrimitiveFunctionalInterfaces.exponentiationLong.andThen(PrimitiveFunctionalInterfaces.exponentiationLong).applyAsLong(2L);
        Assert.assertEquals(MESSAGE_MULT_RESULT, 16, result);
    }

    @Test
    public void testComposeLong2() {
        // compose format
        long result = PrimitiveFunctionalInterfaces.exponentiationLong.compose(PrimitiveFunctionalInterfaces.add1Long).applyAsLong(2);

        Assert.assertEquals(MESSAGE_MULT_RESULT, 9, result);

        // step by step
        result = PrimitiveFunctionalInterfaces.add1Long.applyAsLong(2L);
        result = PrimitiveFunctionalInterfaces.exponentiationLong.applyAsLong(result);

        Assert.assertEquals(MESSAGE_MULT_RESULT, 9, result);
    }

    @Test
    public void testAndThenLong2() {
        // compose format
        long result = PrimitiveFunctionalInterfaces.exponentiationLong.andThen(
                PrimitiveFunctionalInterfaces.sub5Long
        ).applyAsLong(6L);

        Assert.assertEquals(MESSAGE_MULT_RESULT, 31, result);

        // step by step

        result = PrimitiveFunctionalInterfaces.exponentiationLong.applyAsLong(6L);
        result = PrimitiveFunctionalInterfaces.sub5Long.applyAsLong(result);

        Assert.assertEquals(MESSAGE_MULT_RESULT, 31, result);
    }

    @Test
    public void testComposeLong3() {
        // compose format
        long result = PrimitiveFunctionalInterfaces.exponentiationLong.compose(
                PrimitiveFunctionalInterfaces.sub5Long.compose(
                        PrimitiveFunctionalInterfaces.add1Long
                )
        ).applyAsLong(7L);

        Assert.assertEquals(MESSAGE_MULT_RESULT, 9, result);

        // step by step
        result = PrimitiveFunctionalInterfaces.add1Long.applyAsLong(7L);
        result = PrimitiveFunctionalInterfaces.sub5Long.applyAsLong(result);
        result = PrimitiveFunctionalInterfaces.exponentiationLong.applyAsLong(result);

        Assert.assertEquals(MESSAGE_MULT_RESULT, 9, result);
    }

    @Test
    public void testAndThenLong3() {
        // compose format
        Long result = PrimitiveFunctionalInterfaces.exponentiationLong.andThen(
                PrimitiveFunctionalInterfaces.sub5Long.andThen(
                        PrimitiveFunctionalInterfaces.add1Long
                )
        ).applyAsLong(2L);

        Assert.assertEquals(MESSAGE_MULT_RESULT, 0, result.longValue());

        // step by step

        result = PrimitiveFunctionalInterfaces.exponentiationLong.applyAsLong(2L);
        result = PrimitiveFunctionalInterfaces.sub5Long.applyAsLong(result);
        result = PrimitiveFunctionalInterfaces.add1Long.applyAsLong(result);

        Assert.assertEquals(MESSAGE_MULT_RESULT, 0, result.longValue());
    }

}
