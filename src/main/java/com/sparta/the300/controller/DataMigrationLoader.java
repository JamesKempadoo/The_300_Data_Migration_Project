package com.sparta.the300.controller;

import com.sparta.the300.loggers.CustomLoggerConfiguration;
import com.sparta.the300.model.Employee;
import com.sparta.the300.view.DisplayManager;

import java.util.*;
import java.util.logging.Level;
import java.util.regex.Pattern;

public class DataMigrationLoader {
    private static final String DEFAULT_FILENAME = "src/main/resources/EmployeeRecordsLarge.csv";
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
                pattern = Pattern.compile("[yn]");
                threaded = in.next(pattern);
                if (threaded.equals("y")) {
                    numOfThreads = Integer.parseInt(in.next());
                }
                in.nextLine();
                break;
            } catch (Exception e) {
                DisplayManager.printWrongInputMessage();
                in.nextLine();
            }
        } while (true);

        if (filename.equals("d")) {
            filename = DEFAULT_FILENAME;
        }

        CustomLoggerConfiguration.myLogger.log(Level.INFO,
                "Reading file " + filename + " with " + numOfThreads + " threads.");
        long start = System.nanoTime();
        CSVReader.readDataFile(filename, numOfThreads, threaded.equals("y"));
        long end = System.nanoTime();
        CustomLoggerConfiguration.myLogger.log(Level.INFO,
                "File reading just ended with execution time:" + ((double) (end-start)/1000000000));

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
            int column = in.nextInt();
            System.out.println("Type in your filter below:");
            in.nextLine();

            String filter;
            if (column == 0) {
                filter = in.nextLine();
            } else {
                filter = "%" + in.nextLine() + "%";
            }
            CSVReader.getEmployeeDAO().selectIndividualRecords(column, filter);
        } catch (InputMismatchException | ArrayIndexOutOfBoundsException e) {
            System.out.println("Wrong option entered");
        }
    }
}
