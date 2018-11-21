package com.example.java8;

import java.util.function.Function;

import org.apache.commons.lang3.BooleanUtils;
import org.junit.Test;

public class FunctonsTest {

    @Test
    public void chain() {
        Function<Integer, Integer> muliply2 = (i) -> i * i;


        int result = muliply2.andThen(muliply2).andThen(muliply2).andThen(muliply2).apply(2);
        System.out.println(result);
    }

    @Test
    public void base() {
        Function<Integer,String> oneParam = (i) -> Integer.toString(i);

        Function<Integer,String> converter = (i)-> Integer.toString(i);

        Function<String, Integer> reverse = (s)-> Integer.parseInt(s);


        System.out.println(converter.apply(3).length());
        System.out.println(converter.andThen(reverse).apply(30).byteValue());
    }

    @Test
    public void name() {
        System.out.println(BooleanUtils.isFalse(null));
        System.out.println(BooleanUtils.isFalse(Boolean.valueOf(false)));
        System.out.println(BooleanUtils.isFalse(Boolean.valueOf(true)));
    }
}
