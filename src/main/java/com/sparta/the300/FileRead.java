package com.sparta.the300;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class FileRead {

    public static ArrayList<Employee> readCSV(String filename) {
        ArrayList<Employee> employeeArrayList = new ArrayList<>();
        try (FileReader fileReader = new FileReader(filename);
             BufferedReader bufferedReader = new BufferedReader(fileReader)){
            String headings = bufferedReader.readLine();
            SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
            String line;
            while ((line = bufferedReader.readLine()) != null) {

                Employee employee = generateEmployee(line, formatter);

                employeeArrayList.add(employee);
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return employeeArrayList;
    }

    private static Employee generateEmployee(String line, SimpleDateFormat formatter) throws ParseException {
        String[] tokens;
        tokens = line.split(",");
        return new Employee(tokens);
    }
}
