package com.example.java8.predicate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.example.java8.model.ResultStatus;
import com.example.java8.model.SampleContext;

public class SampleContextPredicatesTest {

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
    public void testSuccess() {
        boolean result = SampleContextPredicates
                .success
                .test(SampleContext.builder().
                        resultStatus(ResultStatus.SUCCESS)
                        .build());
        Assert.assertTrue("Not success", result);
    }

    @Test
    public void testSuccessFalse() {
        boolean result = SampleContextPredicates
                .success
                .test(SampleContext.builder().
                        resultStatus(ResultStatus.FAIL)
                        .build());
        Assert.assertFalse("success", result);
    }

    @Test
    public void testSuccessFilter() {
        List<SampleContext> list = sampleContextList.stream()
                .filter(SampleContextPredicates.success)
                .collect(Collectors.toList());

        list.forEach(sampleContext -> Assert.assertEquals("No success", ResultStatus.SUCCESS, sampleContext.getResultStatus()));
    }

    @Test
    public void testSuccessOrPositiveOrderFilter() {
        List<SampleContext> list = sampleContextList.stream()
                .filter(SampleContextPredicates.success.or(SampleContextPredicates.positiveOrder()))
                .collect(Collectors.toList());

        list.forEach(sampleContext -> Assert.assertTrue("Not success or order positive",
                ResultStatus.SUCCESS.equals(sampleContext.getResultStatus())
                || sampleContext.getOrder() > 0)
        );
    }

    @Test
    public void testSuccessAndPositiveOrderFilter() {
        List<SampleContext> list = sampleContextList.stream()
                .filter(SampleContextPredicates.success.and(SampleContextPredicates.positiveOrder()))
                .collect(Collectors.toList());

        list.forEach(sampleContext -> Assert.assertTrue("Not success and order positive",
                ResultStatus.SUCCESS.equals(sampleContext.getResultStatus())
                        && sampleContext.getOrder() > 0)
        );
    }
}
