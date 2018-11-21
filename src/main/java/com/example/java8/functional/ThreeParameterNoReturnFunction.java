package com.example.java8.functional;

@FunctionalInterface
public interface ThreeParameterNoReturnFunction<X,Y,Z> {
    void executeStuff(X x,Y y, Z z);
}
