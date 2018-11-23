package com.example.java8.supplier;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

import org.junit.Assert;
import org.junit.Test;

public class CustomSuppliersTest {

    @Test
    public void testStreamSupplier() {
        Integer[] integers = {1,2,3,4,5,6,7,8};

        Supplier<Stream<Integer>> intSuplier = CustomSuppliers.streamSuplier(integers);

        Assert.assertNotEquals("Streams are same",
                intSuplier.get(),
                intSuplier.get());

        // list equivalent
        List<Integer> intList = Arrays.asList(integers);

        Assert.assertNotEquals("Streams are same",
                intList.stream(),
                intList.stream());
    }

    @Test
    public void testListSupplier() {
        Integer[] integers = {1,2,3,4,5,6,7,8};

        Supplier<List<Integer>> intSuplier = CustomSuppliers.listSuplier(integers);

        Assert.assertNotEquals("Streams are same",
                intSuplier.get(),
                intSuplier.get());
    }
}
