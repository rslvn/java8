package com.example.java8.supplier;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class CustomSuppliers {

    private CustomSuppliers() {
        // for sonar
    }

    public static <T> Supplier<Stream<T>> streamSuplier(T... t) {
        return () -> Stream.of(t);
    }

    public static <T> Supplier<List<T>> listSuplier(T... t) {
        return () -> Arrays.asList(t);
    }

}
