package com.sparta.the300;

import java.sql.Connection;
import java.util.List;

public class ThreadTask implements Runnable{
    private final List<Employee> sublist;
    private final EmployeeDAO employeeDAO;


    public ThreadTask(List<Employee> sublist, EmployeeDAO employeeDAO) {
        this.sublist = sublist;
        this.employeeDAO = employeeDAO;
    }


    @Override
    public void run() {
        Connection connection = employeeDAO.connectingToDataBase();
        employeeDAO.insertIntoTable(sublist, connection);
        employeeDAO.commit(connection);

    }
}
