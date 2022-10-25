package com.sparta.the300;

public class DataMigrationLoader {
    public static void start() {
        int x = CSVReader.readDataFile("src/main/resources/EmployeeRecords.csv").size();
        System.out.println(x);
        System.out.println(CSVReader.getCorruptedEntries().size());
        System.out.println(x + CSVReader.getCorruptedEntries().size());
    }
}
