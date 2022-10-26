package com.sparta.the300;

import java.util.HashSet;

public class DataMigrationLoader {
    public static void start() {
        HashSet<Employee> validEntries = CSVReader.readDataFile("src/main/resources/EmployeeRecords.csv");
        EmployeeDAO employeeDAO = new EmployeeDAO();
        employeeDAO.createEmployeeTable();
        employeeDAO.insertIntoTable(validEntries);
    }
}
