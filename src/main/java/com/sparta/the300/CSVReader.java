package com.sparta.the300;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashSet;

public class CSVReader {

    private static final ArrayList<Employee> corruptedEntries = new ArrayList<>();

    private static String headings = null;

    public static HashSet<Employee> readDataFile(String filename) {
        FileReader fileReader;
        try {
            fileReader = new FileReader(filename);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        return CSVReader.readDataFile(bufferedReader);
    }

    private static HashSet<Employee> readDataFile(BufferedReader br) {
        HashSet<Employee> employeesSet = new HashSet<>();
        try {
            headings = br.readLine();
            String line;
            while ((line = br.readLine()) != null) {
                Employee employee = generateEmployee(line);
                if(!ValidationCheck.isEmployeeValid(employee) || !employeesSet.add(employee)) {
                    corruptedEntries.add(employee);
                }

            }
        } catch (IOException | ParseException ex) {
            ex.printStackTrace();
            System.exit(0);
        }
        return employeesSet;
    }

    private static Employee generateEmployee(String line) throws ParseException {
        String[] tokens;
        tokens = line.split(",");
        return new Employee(tokens);
    }

    public static ArrayList<Employee> getCorruptedEntries() {
        return corruptedEntries;
    }

    public static String getHeadings() {
        return headings;
    }
}
