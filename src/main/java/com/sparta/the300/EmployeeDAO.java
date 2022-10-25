package com.sparta.the300;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.List;
import java.util.Properties;

public class EmployeeDAO {
    private static Properties properties = new Properties();
    private static final String URL = "jdbc:mysql://localhost:3306/employeedb";
    private static Connection connection = null;


    private static final String SELECT_EMPLOYEES = "SELECT * FROM employees;";
    private static final String SELECT_COUNT_EMPLOYEES = "SELECT COUNT(*) FROM employees";
    private static final String DROP_TABLE = "DROP TABLE employees";
    private static final String INSERT_INTO = "INSERT INTO employees " +
            "(employee_id, title, first_name, middle_initial, last_name, gender, " +
            "email, birth_date, join_date, salary) " +
            "VALUES(?,?,?,?,?,?,?,?,?,?);";
    private static final String CREATE_TABLE = "CREATE TABLE employees (" +
            "employee_id VARCHAR(10) NOT NULL PRIMARY KEY,\n" +
            "title VARCHAR(5)," +
            "first_name VARCHAR(30)," +
            "middle_initial CHAR(1)," +
            "last_name VARCHAR(30)," +
            "gender CHAR(1)," +
            "email VARCHAR(40)," +
            "birth_date DATE," +
            "join_date DATE," +
            "salary INTEGER" +
            ");";

    private void connectingToDataBase(){
        try{
            properties.load(new FileReader("src/main/resources/login.properties"));
            connection = DriverManager.getConnection(URL, properties.getProperty("username"), properties.getProperty("password"));
        }catch (IOException e){
            //log
            e.printStackTrace();
        }catch (SQLException e){
            //log
            e.printStackTrace();
        }catch (Exception e){
            //lgo
            e.printStackTrace();
        }
    }
    private void disconnectingFromDatabase(){
        try{
            connection.close();
        }catch (SQLException e){
            //log
            e.printStackTrace();
        }catch (Exception e){
            //lgo
            e.printStackTrace();
        }
    }

    private boolean employeeTableCheck(){
        try {
            ResultSet resultSet = connection.getMetaData().getTables(null,
                    null,"employees",new String[] {"TABLE"});
            while (resultSet.next()) {
                String databaseName = resultSet.getString("TABLE_NAME");
                if(databaseName.equals("employees")){
                    return true;
                }
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public void createTable(){
        try (PreparedStatement preparedStatement = connection.prepareStatement(CREATE_TABLE);){
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropTable(){
        try (PreparedStatement preparedStatement = connection.prepareStatement(DROP_TABLE);){
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int countAllEmployees() {
        tryCon();
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO);){
            ResultSet resultSet = preparedStatement.executeQuery(SELECT_COUNT_EMPLOYEES);
            if (resultSet != null) {
                while (resultSet.next()) {
                    return resultSet.getInt(1);
                }
            } else {
                System.out.println("No records in the table");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return 0;
    }

    public void createEmployeeTable(){
        try{
            tryCon();
            if (employeeTableCheck()){
                dropTable();
                //log
            }
            createTable();
                //log
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            disconnectingFromDatabase();
        }
    }

    private void tryCon(){
        try {
            if (connection == null ||connection.isClosed()){
                connectingToDataBase();}
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void selectEmployees(){
        try {
            tryCon();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_EMPLOYEES);
            if (resultSet!=null){
                while (resultSet.next()){
                    System.out.println(resultSet.getString(1)+ " " +
                            resultSet.getString(2)+ " " +
                            resultSet.getString(3)+ " " +
                            resultSet.getString(4)+ " " +
                            resultSet.getString(5)+ " " +
                            resultSet.getString(6)+ " " +
                            resultSet.getString(7)+ " " +
                            resultSet.getString(8)+ " " +
                            resultSet.getString(9)+ " " +
                            resultSet.getInt(10));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void insertIntoTable(List<Employee> employees) {
        tryCon();
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO)) {
            if (connection == null || connection.isClosed()) {
                connectingToDataBase();
            }
            for (Employee employee : employees) {
                preparedStatement.setInt(1, employee.getIdNumber());
                preparedStatement.setString(2, employee.getNamePrefix());
                preparedStatement.setString(3, employee.getFirstName());
                preparedStatement.setString(4, employee.getMiddleInitial());
                preparedStatement.setString(5, employee.getLastName());
                preparedStatement.setString(6, employee.getGender());
                preparedStatement.setString(7, employee.getEmail());
                preparedStatement.setDate(8, new java.sql.Date(employee.getDateOfBirth().getTime()));
                preparedStatement.setDate(9, new java.sql.Date(employee.getDateOfEmployment().getTime()));
                preparedStatement.setInt(10, employee.getSalary());
                preparedStatement.executeUpdate();
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            disconnectingFromDatabase();
        }
    }
}
