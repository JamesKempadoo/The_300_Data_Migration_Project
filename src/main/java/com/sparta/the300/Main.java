package com.sparta.the300;

public class Main {
    public static void main(String[] args) {
        long start = System.nanoTime();
        DataMigrationLoader.start();
        long end = System.nanoTime();
        System.out.println("Execution time: " + (end-start) + " nanoseconds");
    }
}
