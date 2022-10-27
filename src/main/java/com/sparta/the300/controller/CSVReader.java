package com.sparta.the300.controller;

import com.sparta.the300.model.EmployeeDAO;
import com.sparta.the300.model.BatchWorker;
import com.sparta.the300.model.ValidationCheck;
import com.sparta.the300.model.Employee;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class CSVReader {

    private static final EmployeeDAO employeeDAO = new EmployeeDAO();
    private static final ArrayList<Employee> corruptedEntries = new ArrayList<>();
    private static final ArrayList<Employee> duplicatedEntries = new ArrayList<>();

    private static final ArrayList<Employee> batchEntries = new ArrayList<>();

    private static final ArrayList<Thread> threads = new ArrayList<>();
    private static String headings = null;

    public static HashSet<Employee> readDataFile(String filename, int numOfThreads, boolean isThreaded) {
        FileReader fileReader;
        long totalLines;
        try {
            fileReader = new FileReader(filename);
            totalLines = Files.lines(Path.of(filename)).count() - 1;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        return CSVReader.readDataFile(bufferedReader, totalLines, numOfThreads, isThreaded);
    }

    private static HashSet<Employee> readDataFile(BufferedReader br, long totalLines, int numOfThreads, boolean isThreaded) {
        HashSet<Employee> employeesSet = new HashSet<>();
        employeeDAO.createEmployeeTable();
        try {
            headings = br.readLine();
            String line;
            int lowBound = 0;
            int highBound = 0;
            while ((line = br.readLine()) != null) {
                Employee employee = generateEmployee(line);
                if (!ValidationCheck.isEmployeeValid(employee)) {
                    corruptedEntries.add(employee);
                } else if (!employeesSet.add(employee)) {
                    duplicatedEntries.add(employee);
                } else {
                    batchEntries.add(employee);
                }
                if ((isThreaded) && (highBound++ - lowBound) >= totalLines / numOfThreads) {
                    createThreadWithBatch(lowBound, highBound);
                    lowBound = highBound;
                }
            }
            createThreadWithBatch(lowBound, batchEntries.size());
            for (Thread thread : threads) {
                thread.join();
            }
        } catch (IOException | ParseException ex) {
            ex.printStackTrace();
            System.exit(0);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return employeesSet;
    }

    private static void createThreadWithBatch(int lowBound, int highBound) {
        System.out.println(lowBound + "::" + highBound);
        List<Employee> batchList = new ArrayList<>(batchEntries.subList(lowBound, highBound));
        Thread thread = new Thread(new BatchWorker(batchList, employeeDAO));
        threads.add(thread);
        thread.start();
    }

    private static Employee generateEmployee(String line) throws ParseException {
        String[] tokens;
        tokens = line.split(",");
        return new Employee(tokens);
    }

    public static ArrayList<Employee> getCorruptedEntries() {
        return corruptedEntries;
    }

    public static ArrayList<Employee> getDuplicatedEntries() {
        return duplicatedEntries;
    }

    public static String getHeadings() {
        return headings;
    }
}
