package com.sparta.the300.controller;

import com.sparta.the300.loggers.CustomLoggerConfiguration;
import com.sparta.the300.model.Employee;
import com.sparta.the300.model.EmployeeDAO;
import com.sparta.the300.view.DisplayManager;

import java.util.*;
import java.util.logging.Level;
import java.util.regex.Pattern;

public class DataMigrationLoader {
    private static final String DEFAULT_FILENAME = "src/main/resources/EmployeeRecords.csv";
    private static CustomLoggerConfiguration customLoggerConfiguration = CustomLoggerConfiguration.getInstance();

    public static void start() {
        Scanner in = new Scanner(System.in);
        DisplayManager.displayInitialConfiguration();
        String filename;
        String threaded;
        int numOfThreads = 0;
        do {
            try {
                Pattern pattern = Pattern.compile("d|.*.csv");
                filename = in.next(pattern);
                pattern = Pattern.compile("y|n");
                threaded = in.next(pattern);
                if (threaded.equals("y")) {
                    numOfThreads = Integer.parseInt(in.next());
                }
                break;
            } catch (Exception e) {
                DisplayManager.printWrongInputMessage();
                in.nextLine();
            }
        } while (true);

        if (filename.equals("d")) {
            filename = DEFAULT_FILENAME;
        }

        customLoggerConfiguration.myLogger.log(Level.INFO,
                "Reading file " + filename + " with " + numOfThreads + "threads.");
        long start = System.nanoTime();
        CSVReader.readDataFile(filename, numOfThreads, threaded.equals("y"));
        long end = System.nanoTime();
        CustomLoggerConfiguration.myLogger.log(Level.INFO,
                "File reading just ended.");

        HashSet<Employee> validEntries = CSVReader.getValidEntries();
        int duplicatedRecords = CSVReader.getDuplicatedEntries().size();
        int corruptedRecords = CSVReader.getCorruptedEntries().size() + duplicatedRecords;
        DisplayManager.printPersistingResults(start, end, validEntries.size(), corruptedRecords, duplicatedRecords);

        DisplayManager.printRetrieveDataOption();
        if (in.next().equals("y")) {
            retrieval(in);
        }
    }

    private static void retrieval(Scanner in) {
        DisplayManager.printRecordRetrievalMenu();
        try {
            EmployeeDAO employeeDAO = CSVReader.getEmployeeDAO();
            String column = employeeDAO.getColumnToSearchIn(in.nextInt());
            System.out.println("Type in your filter below:");
            in.nextLine();

            String filter;
            if (column.equals("employee_id")) {
                filter = in.nextLine();
            } else {
                filter = "%" + in.nextLine() + "%";
            }
            employeeDAO.selectIndividualRecords(column, filter);
        } catch (InputMismatchException | ArrayIndexOutOfBoundsException e) {
            System.out.println("Wrong option entered");
        }
    }
}
