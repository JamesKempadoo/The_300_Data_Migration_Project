package com.sparta.the300;

import com.sparta.the300.view.DisplayManager;

import java.util.*;

public class DataMigrationLoader {
    private static Scanner sc = new Scanner(System.in);
    public static void start() {
        System.out.println("Enter 1 to add to database, 2 to access records.");
        try{
            int option = sc.nextInt();
            EmployeeDAO employeeDAO = new EmployeeDAO();
            if (option == 1) {
                employeeDAO = creation();
            } else if (option == 2) {
                retrieval(employeeDAO);
            } else {
                start();
            }
        } catch (InputMismatchException e){
            System.out.println("Option not valid");
        }

    }

    public static void concurrently(HashSet<Employee> validEntries, EmployeeDAO employeeDAO, int numberOfThreads){
        Thread[] threads = new Thread[numberOfThreads+1];
        ArrayList<Employee> arrayList = new ArrayList<>(validEntries);
        int i = 0;
        int threadCounter= 0;
        int x1 = arrayList.size()/numberOfThreads;

        for (i=0; i<= arrayList.size(); i+=arrayList.size()/numberOfThreads){
            List<Employee> subArray;
            if (i+x1 > arrayList.size()){
                subArray = arrayList.subList(i, arrayList.size());

            }else{
                subArray= arrayList.subList(i,i+x1);
            }
            //employeeDAO.tryCon();
            threads[threadCounter] = new Thread(new ThreadTask(subArray,employeeDAO));
            threads[threadCounter].setName("Thread: " + threadCounter);
            threads[threadCounter].start();
            threadCounter++;
        }
        for (int j = 0; j < threadCounter; j++) {
            try {
                threads[j].join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
    private static void retrieval(EmployeeDAO employeeDAO) {
        DisplayManager.printRecordRetrievalMenu();
        try {
            String column = employeeDAO.getColumnToSearchIn(sc.nextInt());
            System.out.println("Type in your filter below:");
            sc.nextLine();
            String filter = "%" + sc.nextLine() + "%";
            employeeDAO.selectIndividualRecords(column, filter);
        } catch(InputMismatchException | ArrayIndexOutOfBoundsException e){
            System.out.println("Wrong option entered");

        }
    }

    private static EmployeeDAO creation() {
        long start = System.nanoTime();
        HashSet<Employee> validEntries = CSVReader.readDataFile("src/main/resources/EmployeeRecordsLarge.csv");
        EmployeeDAO employeeDAO = new EmployeeDAO();
        employeeDAO.createEmployeeTable();
        concurrently(validEntries, employeeDAO, 64);
        long end = System.nanoTime();

        int duplicatedRecords = CSVReader.getDuplicatedEntries().size();
        int corruptedRecords = CSVReader.getCorruptedEntries().size() + duplicatedRecords;
        DisplayManager.printPersistingResults(start, end, validEntries.size(), corruptedRecords, duplicatedRecords,0);
        return employeeDAO;
    }
}
