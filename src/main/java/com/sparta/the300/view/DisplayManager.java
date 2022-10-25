package com.sparta.the300.view;

import com.sparta.the300.Employee;

import java.util.List;

public class DisplayManager {

    public static void printResults(int uncorruptedRecords, int corruptedRecords, int duplicated,
                                    int withMissingFields){
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
}
