package com.sparta.the300.model;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class BatchWorker implements Runnable{
    private final List<Employee> sublist;
    private final EmployeeDAO employeeDAO;


    public BatchWorker(List<Employee> sublist, EmployeeDAO employeeDAO) {
        this.sublist = sublist;
        this.employeeDAO = employeeDAO;
    }


    @Override
    public void run() {
        Connection connection = employeeDAO.connectingToDataBase();
        employeeDAO.insertIntoTable(sublist, connection);
        //employeeDAO.commit(connection);

        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}
