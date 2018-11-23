package com.example.java8.predicate;

import org.junit.Assert;
import org.junit.Test;

public class CustomPredicatesTest {

    @Test
    public void testZero() {
        boolean result = CustomPredicates.isZero.test(0);
        Assert.assertTrue("Not zero", result);
    }

    @Test
    public void testZeroFalse() {
        boolean result = CustomPredicates.isZero.test(1);
        Assert.assertFalse("zero", result);
    }

    @Test
    public void testPositive() {
        boolean result = CustomPredicates.isPositive.test(5);
        Assert.assertTrue("Not positive", result);
    }

    @Test
    public void testPositiveFalse() {
        boolean result = CustomPredicates.isPositive.test(-1);
        Assert.assertFalse("positive", result);
    }

    @Test
    public void testNegative() {
        boolean result = CustomPredicates.isNegative.test(-1);
        Assert.assertTrue("Not negative", result);
    }

    @Test
    public void testNegativeFalse() {
        boolean result = CustomPredicates.isNegative.test(5);
        Assert.assertFalse("negative", result);
    }

    @Test
    public void testNull() {
        boolean result = CustomPredicates.isNull.test(null);
        Assert.assertTrue("Not null", result);
    }

    @Test
    public void testNullFalse() {
        boolean result = CustomPredicates.isNull.test(new Object());
        Assert.assertFalse("The object is null", result);
    }

    @Test
    public void testNonNull() {
        boolean result = CustomPredicates.isNonNull.test(new Object());
        Assert.assertTrue("The object is null", result);
    }

    @Test
    public void testNonNullFalse() {
        boolean result = CustomPredicates.isNonNull.test(null);
        Assert.assertFalse("not null", result);
    }
}
