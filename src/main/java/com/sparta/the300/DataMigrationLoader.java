package com.sparta.the300;

public class DataMigrationLoader {
    public static void start() {
        FileRead.readCSV("src/main/resources/EmployeeRecords.csv");
    }
}
