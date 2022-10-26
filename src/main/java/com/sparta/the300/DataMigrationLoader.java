package com.sparta.the300;

import com.sparta.the300.view.DisplayManager;

import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class DataMigrationLoader {
    private static Scanner sc = new Scanner(System.in);
    public static void start() {
        EmployeeDAO employeeDAO = creation();

        retrieval(employeeDAO);
    }

    private static void retrieval(EmployeeDAO employeeDAO) {
        DisplayManager.printRecordRetrievalMenu();
        try {
            String column = employeeDAO.getColumnToSearchIn(sc.nextInt());
            System.out.println("Type in your filter below:");
            sc.nextLine();
            String filter = "%" + sc.nextLine() + "%";
            employeeDAO.selectIndividualRecords(column, filter);
        } catch(InputMismatchException e){
            System.out.println("Wrong option entered");
        }
    }

    private static EmployeeDAO creation() {
        long start = System.nanoTime();
        HashSet<Employee> validEntries = CSVReader.readDataFile("src/main/resources/EmployeeRecords.csv");
        EmployeeDAO employeeDAO = new EmployeeDAO();
        employeeDAO.createEmployeeTable();
        employeeDAO.insertIntoTable(validEntries);
        long end = System.nanoTime();

        DisplayManager.printPersistingResults(start, end, 0,0,0,0);
        return employeeDAO;
    }
}
