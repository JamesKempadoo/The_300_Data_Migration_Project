package com.sparta.the300;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

public class CSVReaderTest {
    @Test
    @DisplayName("Check whether the second entry with same ID is interpreted as corrupted")
    void checkSecondEntryWithSameIdIsParsedToCorrupted() {
        HashSet<Employee> employeeHashSet = CSVReader.readDataFile("src/main/resources/TestEmployeeRecords.csv");
        for(Employee employee:employeeHashSet) {
            Assertions.assertEquals("Serafina",employee.getFirstName());
        }
        for (Employee employee:CSVReader.getCorruptedEntries()) {
            Assertions.assertEquals("Juliette",employee.getFirstName());
        }
    }
}
