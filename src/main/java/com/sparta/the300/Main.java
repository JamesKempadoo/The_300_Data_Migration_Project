package com.sparta.the300;

import com.sparta.the300.controller.DataMigrationLoader;

public class Main {
    public static void main(String[] args) {
        long start = System.nanoTime();
        DataMigrationLoader.start();
        long end = System.nanoTime();
        System.out.println((start-end));
    }
}
