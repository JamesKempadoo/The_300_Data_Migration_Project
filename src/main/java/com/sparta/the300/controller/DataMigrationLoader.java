package com.sparta.the300.controller;

import com.sparta.the300.model.BatchWorker;
import com.sparta.the300.model.Employee;
import com.sparta.the300.model.EmployeeDAO;
import com.sparta.the300.view.DisplayManager;

import java.util.*;

public class DataMigrationLoader {
    private static Scanner sc = new Scanner(System.in);

    public static void start() {
        System.out.println("Enter 1 to add to database, 2 to access records.");
        try {
            int option = sc.nextInt();
            EmployeeDAO employeeDAO = new EmployeeDAO();
            if (option == 1) {
                long start = System.nanoTime();
                CSVReader.readDataFile("src/main/resources/EmployeeRecordsLarge.csv", 16, true);
                long end = System.nanoTime();
                HashSet<Employee> validEntries = CSVReader.getValidEntries();
                int duplicatedRecords = CSVReader.getDuplicatedEntries().size();
                int corruptedRecords = CSVReader.getCorruptedEntries().size() + duplicatedRecords;
                DisplayManager.printPersistingResults(start, end, validEntries.size(), corruptedRecords, duplicatedRecords, 0);
            } else if (option == 2) {
                retrieval(employeeDAO);
            } else {
                start();
            }
        } catch (InputMismatchException e) {
            System.out.println("Option not valid");
        }
    }

    private static void retrieval(EmployeeDAO employeeDAO) {
        DisplayManager.printRecordRetrievalMenu();
        try {
            String column = employeeDAO.getColumnToSearchIn(sc.nextInt());
            System.out.println("Type in your filter below:");
            sc.nextLine();
            String filter = "%" + sc.nextLine() + "%";
            employeeDAO.selectIndividualRecords(column, filter);
        } catch (InputMismatchException | ArrayIndexOutOfBoundsException e) {
            System.out.println("Wrong option entered");

        }
    }
}
