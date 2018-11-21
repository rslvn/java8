package com.example.java8.functional;

import java.util.function.IntBinaryOperator;
import java.util.function.IntUnaryOperator;
import java.util.function.LongBinaryOperator;
import java.util.function.LongUnaryOperator;

public class PrimitiveFunctionalInterfaces {

    IntBinaryOperator sumInt = (i, j) -> i + j;
    IntBinaryOperator substractInt = (i, j) -> i - j;
    IntBinaryOperator divideInt = (i, j) -> i / j;
    IntBinaryOperator multiplyInt = (i, j) -> i * j;

    LongBinaryOperator sumLong = (i, j) -> i + j;
    LongBinaryOperator substractLong = (i, j) -> i - j;
    LongBinaryOperator divideLong = (i, j) -> i / j;
    LongBinaryOperator multiplyLong = (i, j) -> i * j;

    IntUnaryOperator exponentiationInt = i -> multiplyInt.applyAsInt(i,i);
    LongUnaryOperator exponentiationLong = i -> multiplyLong.applyAsLong(i,i);

}
