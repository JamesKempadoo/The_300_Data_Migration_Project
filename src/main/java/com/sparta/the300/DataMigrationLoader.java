package com.sparta.the300;

public class DataMigrationLoader {
    public static void start() {
        System.out.println(FileRead.readCSV("src/main/resources/EmployeeRecordsLarge.csv").size());
    }
}
