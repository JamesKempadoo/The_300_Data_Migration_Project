package com.sparta.the300.view;

import com.sparta.the300.Employee;
import com.sparta.the300.EmployeeDAO;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class DisplayManager {

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
                        ">>>>>> " + duplicated + " duplicated\n" +
                        ">>>>>> " + withMissingFields + " with missing fields\n");
    }

    public static void printCorruptedRecords(List<Employee> employees) {
        //After filtering employees

        System.out.println("Corrupted records:\n");

        for(Employee employee: employees){
            System.out.println(employee.toString());
        }

    }

    public static void printRecordRetrievalMenu(){
        String[] headings = EmployeeDAO.headings;
        System.out.println("Select one of these fields to search in:");
                for(int i=0; i< headings.length; i++){
                    System.out.println(i + ". " + headings[i]);
                }
    };

}
