package com.sparta.the300.model;


import com.sparta.the300.loggers.CustomLoggerConfiguration;
import com.sparta.the300.utility.SQLQueries;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

public class EmployeeDAO {
    private static Properties properties = new Properties();
    private static final String URL = "jdbc:mysql://localhost:3306/employeedb";

    public Connection connectingToDataBase() {
        Connection connection = null;
        try {
            properties.load(new FileReader("src/main/resources/login.properties"));
            connection = DriverManager.getConnection(URL, properties.getProperty("username"), properties.getProperty("password"));
            connection.setAutoCommit(false);
        } catch (IOException e) {
            CustomLoggerConfiguration.myLogger.log(Level.WARNING, "IO exception caught.");
            e.printStackTrace();
        } catch (SQLException e) {
            CustomLoggerConfiguration.myLogger.log(Level.WARNING, "SQL exception caught.");
            e.printStackTrace();
        } catch (Exception e) {
            CustomLoggerConfiguration.myLogger.log(Level.WARNING, "Exception caught.");
            e.printStackTrace();
        }
        return connection;
    }

    public void disconnectingFromDatabase(Connection newConnection) {
        try {
            newConnection.close();
        } catch (Exception e) {
            CustomLoggerConfiguration.myLogger.log(Level.WARNING, "SQL exception caught.");
            e.printStackTrace();
        }
    }

    private boolean employeeTableCheck(Connection newConnection) {
        try {
            ResultSet resultSet = newConnection.getMetaData().getTables(null,
                    null, "employees", new String[]{"TABLE"});
            while (resultSet.next()) {
                String databaseName = resultSet.getString("TABLE_NAME");
                if (databaseName.equals("employees")) {
                    return true;
                }
            }
            resultSet.close();
        } catch (SQLException e) {
            CustomLoggerConfiguration.myLogger.log(Level.WARNING, "SQL exception caught.");
            throw new RuntimeException(e);
        }
        return false;
    }

    public void createTable(Connection newConnection) {
        try (PreparedStatement preparedStatement = newConnection.prepareStatement(SQLQueries.CREATE_TABLE)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            CustomLoggerConfiguration.myLogger.log(Level.WARNING, "SQL exception.");
            throw new RuntimeException(e);
        }
    }

    public void dropTable(Connection newConnection) {
        try (PreparedStatement preparedStatement = newConnection.prepareStatement(SQLQueries.DROP_TABLE)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            CustomLoggerConfiguration.myLogger.log(Level.WARNING, "SQL exception.");
            throw new RuntimeException(e);
        }
    }


    public void createEmployeeTable() {
        Connection newConnection = connectingToDataBase();
        try {
            if (employeeTableCheck(newConnection)) {
                dropTable(newConnection);
            }
            createTable(newConnection);
            newConnection.commit();

        } catch (Exception e) {
            CustomLoggerConfiguration.myLogger.log(Level.WARNING, "SQL exception.");
            throw new RuntimeException(e);
        } finally {
            disconnectingFromDatabase(newConnection);
        }
    }


    public void selectEmployees() {
        Connection newConnection = connectingToDataBase();
        try {
            Statement statement = newConnection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQLQueries.SELECT_EMPLOYEES);
            if (resultSet != null) {
                while (resultSet.next()) {
                    System.out.println(resultSet.getString(1) + " " +
                            resultSet.getString(2) + " " +
                            resultSet.getString(3) + " " +
                            resultSet.getString(4) + " " +
                            resultSet.getString(5) + " " +
                            resultSet.getString(6) + " " +
                            resultSet.getString(7) + " " +
                            resultSet.getString(8) + " " +
                            resultSet.getString(9) + " " +
                            resultSet.getInt(10));
                }
            }
        } catch (SQLException e) {
            CustomLoggerConfiguration.myLogger.log(Level.WARNING, "SQL exception.");
            throw new RuntimeException(e);
        }
    }


    public void insertIntoTable(List<Employee> employees, Connection newConnection) {
        try (PreparedStatement preparedStatement = newConnection.prepareStatement(SQLQueries.INSERT_INTO)) {
            for (Employee employee : employees) {
                preparedStatement.setInt(1, employee.getIdNumber());
                preparedStatement.setString(2, employee.getNamePrefix());
                preparedStatement.setString(3, employee.getFirstName());
                preparedStatement.setString(4, employee.getMiddleInitial());
                preparedStatement.setString(5, employee.getLastName());
                preparedStatement.setString(6, employee.getGender());
                preparedStatement.setString(7, employee.getEmail());
                preparedStatement.setDate(8, employee.getDateOfBirth());
                preparedStatement.setDate(9, employee.getDateOfEmployment());
                preparedStatement.setInt(10, employee.getSalary());
                preparedStatement.addBatch();

            }
            preparedStatement.executeBatch();
            newConnection.commit();
        } catch (SQLException e) {
            CustomLoggerConfiguration.myLogger.log(Level.WARNING, "SQL exception.");
            e.printStackTrace();
        }
    }

    public void selectIndividualRecords(int column, String filter) {
        int count = 0;
        Connection newConnection = connectingToDataBase();
        try (PreparedStatement preparedStatement = newConnection.prepareStatement(SQLQueries.SELECT_INDIVIDUAL_RECORDS.replaceFirst("(\\?)+",SQLQueries.columns[column]))) {
            preparedStatement.setString(1, filter);
            System.out.println(preparedStatement);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet != null) {
                while (resultSet.next()) {
                    System.out.println(resultSet.getString(1) + " " +
                            resultSet.getString(2) + " " +
                            resultSet.getString(3) + " " +
                            resultSet.getString(4) + " " +
                            resultSet.getString(5) + " " +
                            resultSet.getString(6) + " " +
                            resultSet.getString(7) + " " +
                            resultSet.getString(8) + " " +
                            resultSet.getString(9) + " " +
                            resultSet.getInt(10));
                    count++;
                }
                CustomLoggerConfiguration.myLogger.log(Level.FINE,
                        count + " records found when searching " + filter + " in column " + column);
                System.out.println(count + " records found.");
            } else {
                CustomLoggerConfiguration.myLogger.log(Level.FINE,
                        "0 records found when searching " + filter + " in column " + column);
                System.out.println("0 records found");
            }


        } catch (SQLException e) {
            CustomLoggerConfiguration.myLogger.log(Level.WARNING, "SQL exception.");
            e.printStackTrace();
        }
    }
}
