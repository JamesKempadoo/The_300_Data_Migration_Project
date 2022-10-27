package com.sparta.the300;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Spliterator;

public class ThreadTask implements Runnable{
    private final List<Employee> sublist;
    private final EmployeeDAO employeeDAO;


    public ThreadTask(List<Employee> sublist, EmployeeDAO employeeDAO) {
        this.sublist = sublist;
        this.employeeDAO = employeeDAO;
    }


    @Override
    public void run() {
        employeeDAO.insertIntoTable(sublist);
    }
}
