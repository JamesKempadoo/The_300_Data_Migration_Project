package com.sparta.the300;

import com.sparta.the300.controller.CSVReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class DataMigrationTest {
    private static final String FILE_PREFIX = "src/main/resources/";
    @Test
    @DisplayName("Check if multithreading works")
    void checkMultithreadingSystem() {
        String fileame = "EmployeeRecords.csv";
        CSVReader.readDataFile(FILE_PREFIX + fileame, 16, true);
        Assertions.assertEquals(10000,
                CSVReader.getCorruptedEntries().size()
                        + CSVReader.getDuplicatedEntries().size()
                        + CSVReader.getValidEntries().size());
    }

}
