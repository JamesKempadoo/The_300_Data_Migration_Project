package com.sparta.the300.view;

import com.sparta.the300.controller.CSVReader;
import com.sparta.the300.model.Employee;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DisplayManager {

    public static void displayInitialConfiguration() {
        System.out.print("""
                Please enter:
                1. The file name ('d' for EmployeeRecordsLarge.csv)
                2. If you want to use threaded version ('y' for yes, 'n' for no)
                3. The number of threads (if you answer 'y' to the above)
                
                Parse your inputs separated by space:""");
    }

    public static void printWrongInputMessage() {
        System.out.print("""
                
                Wrong input! Please enter your inputs as specified above!
                Example Inputs:
                1. example.csv y 16
                2. example1.csv n
                3. d y 32
                
                Parse your inputs separated by space:""");
    }

    public static void printPersistingResults(long start, long end, int uncorruptedRecords, int corruptedRecords,
                                      int duplicated){

        System.out.println("Execution time: " + (end-start) + " nanoseconds");
        System.out.println("Execution time: " + (double) (end-start)/1000000000 + " seconds");
        System.out.println(
                "Summary of records on file:\n" +
                        ">>>>>> Uncorrupted: " + uncorruptedRecords +
                        "\n>>>>>> Corrupted: " + corruptedRecords + "\n");
        System.out.println(
                "Out of the corrupted records:\n" +
                        ">>>>>> " + duplicated + " duplicated");
    }

    public static void printCorruptedRecords(List<Employee> corruptedList, List<Employee> duplicatedList) {

        System.out.println("Corrupted records:\n");

        for(Employee employee: corruptedList){
            System.out.println(employee.toString());
        }

        for(Employee employee: duplicatedList){
            System.out.println(employee.toString());
        }

    }

    public static void printRetrieveDataOption() {
        System.out.print("\n\nDo you want to retrieve data from the database ('y' or 'n'):");
    }

    public static void printRecordRetrievalMenu(){
        List<String> headings;
        headings = Arrays.stream(CSVReader.getHeadings().split(","))
                .filter(s -> !s.equals("Middle Initial") && !s.equals("Salary") && !s.equals("Name Prefix"))
                .collect(Collectors.toList());
        System.out.println("Select one of these fields to search in:");
                for(int i=0; i< headings.size(); i++){
                    System.out.println(i + ". " + headings.get(i));
                }
    }

}
