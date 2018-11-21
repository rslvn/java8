package com.example.java8.functional;

@FunctionalInterface
public interface ThreeParameterFunction<X,Y,Z,R> {
    R apply(X x,Y y, Z z);
}
