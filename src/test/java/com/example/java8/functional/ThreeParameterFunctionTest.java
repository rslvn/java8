package com.example.java8.functional;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ThreeParameterFunctionTest {

    ThreeParameterFunction<String, String, Integer, String> dummy = (x, y, z) -> x + y + z;

    ThreeParameterNoReturnFunction<String, String, Integer> noReturn = (x, y, z) -> {
        System.out.println(dummy.apply(x,y,z));
    };

    @Test
    public void test() {
        Map<String, String> map = new HashMap<>();
        map.put("a", "b");
        map.put("c", "d");
        map.put("e", "f");

        AtomicInteger count = new AtomicInteger(0);
        map.forEach((k, v) -> System.out.println(dummy.apply(k, v, count.getAndIncrement())));


        map.forEach((k, v) -> noReturn.executeStuff(k,v,count.getAndIncrement()));
    }
}
