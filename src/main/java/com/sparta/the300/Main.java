package com.sparta.the300;

import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        long start = System.nanoTime();
        DataMigrationLoader.start();
        long end = System.nanoTime();
        System.out.println("Execution time: " + (end-start) + " nanoseconds");
        System.out.println("Execution time: " + TimeUnit.SECONDS.convert((end-start),TimeUnit.NANOSECONDS) + " seconds");
    }
}
