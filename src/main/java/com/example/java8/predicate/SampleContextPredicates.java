package com.example.java8.predicate;

import java.util.function.Predicate;

import com.example.java8.model.ResultStatus;
import com.example.java8.model.SampleContext;

public class SampleContextPredicates {

    private SampleContextPredicates() {
        // for sonar
    }

    public static Predicate<SampleContext> success = sampleContext -> ResultStatus.SUCCESS.equals(sampleContext.getResultStatus());

    public static Predicate<SampleContext> fail() {
        return sampleContext -> ResultStatus.FAIL.equals(sampleContext.getResultStatus());
    }

    public static Predicate<SampleContext> nameNotNull = CustomPredicates.isNonNull();

    public static Predicate<SampleContext> positiveOrder() {
        return sampleContext -> CustomPredicates.isPositive.test(sampleContext.getOrder());
    }

}
