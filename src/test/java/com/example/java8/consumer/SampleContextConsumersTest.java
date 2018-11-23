package com.example.java8.consumer;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.example.java8.model.ResultStatus;
import com.example.java8.model.SampleContext;

public class SampleContextConsumersTest {

    private List<SampleContext> sampleContextList;

    @Before
    public void setUp() {
        sampleContextList = Arrays.asList(
                SampleContext.builder().name("name1").resultStatus(ResultStatus.SUCCESS).build(),
                SampleContext.builder().name("name2").order(1).resultStatus(ResultStatus.FAIL).build(),
                SampleContext.builder().order(2).resultStatus(ResultStatus.SUCCESS).build(),
                SampleContext.builder().name("name3").order(3).resultStatus(ResultStatus.FAIL).build(),
                SampleContext.builder().order(4).resultStatus(ResultStatus.FAIL).build(),
                SampleContext.builder().name("name4").order(5).resultStatus(ResultStatus.SUCCESS).build()
        );
    }

    @Test
    public void testPrint() {
        sampleContextList.forEach(SampleContextConsumers.printOrderAndName());
    }

    @Test
    public void testIncreaceOrderAndPrint() {
        sampleContextList.forEach(SampleContextConsumers.increaseOrderId
                .andThen(SampleContextConsumers.printOrderAndName()));
    }
}
