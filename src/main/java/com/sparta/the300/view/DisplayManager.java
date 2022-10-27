package com.sparta.the300.view;

import com.sparta.the300.controller.CSVReader;
import com.sparta.the300.model.Employee;
import com.sparta.the300.model.EmployeeDAO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class DisplayManager {

    public static void displayInitialConfiguration() {
        System.out.print("""
                Please enter:
                1. The file name ('d' for EmployeeRecordsLarge.csv)
                2. If you want to use threaded version ('y' for yes, 'n' for no)
                3. The number of threads (if you answer 'y' to the above)
                
                Parse your inputs seperated by space:""");
    }

    public static void printWrongInputMessage() {
        System.out.print("""
                
                Wrong input! Please enter your inputs as specified above!
                Example Inputs:
                1. example.csv y 16
                2. example1.csv n
                3. d y 32
                
                Parse your inputs seperated by space:""");
    }

    public static void printPersistingResults(long start, long end, int uncorruptedRecords, int corruptedRecords,
                                      int duplicated,
                                    int withMissingFields){

        System.out.println("Execution time: " + (end-start) + " nanoseconds");
        System.out.println("Execution time: " + TimeUnit.SECONDS.convert((end-start),TimeUnit.NANOSECONDS) + " seconds");
        System.out.println(
                "Summary of records on file:\n" +
                        ">>>>>> Uncorrupted: " + uncorruptedRecords +
                        "\n>>>>>> Corrupted: " + corruptedRecords + "\n");
        System.out.println(
                "Out of the corrupted records:\n" +
                        ">>>>>> " + duplicated + " duplicated");
    }

    public static void printCorruptedRecords(List<Employee> employees) {
        //After filtering employees

        System.out.println("Corrupted records:\n");

        for(Employee employee: employees){
            System.out.println(employee.toString());
        }

    }

    public static void printRetrieveDataOption() {
        System.out.print("\n\nDo you want to retrieve data from the database ('y' or 'n'):");
    }

    public static void printRecordRetrievalMenu(){
        List<String> headings = new ArrayList<>();
        headings = Arrays.stream(CSVReader.getHeadings().split(","))
                .filter(s -> !s.equals("Middle Initial") && !s.equals("Salary") && !s.equals("Name Prefix"))
                .collect(Collectors.toList());
        System.out.println("Select one of these fields to search in:");
                for(int i=0; i< headings.size(); i++){
                    System.out.println(i + ". " + headings.get(i));
                }
    }

}
