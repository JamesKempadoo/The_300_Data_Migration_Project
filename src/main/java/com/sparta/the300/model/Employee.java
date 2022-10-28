package com.sparta.the300.model;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Employee {
    private int idNumber;
    private String namePrefix;
    private String firstName;
    private String middleInitial;
    private String lastName;
    private String gender;
    private String email;
    private Date dateOfBirth;
    private Date dateOfEmployment;
    private int salary;

    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("MM/dd/yyyy");

    public Employee(String[] tokens) throws ParseException {
        this.idNumber = Integer.parseInt(tokens[0]);
        this.namePrefix = tokens[1];
        this.firstName = tokens[2];
        this.middleInitial = tokens[3];
        this.lastName = tokens[4];
        this.gender = tokens[5];
        this.email = tokens[6];
        this.dateOfBirth = new Date(SIMPLE_DATE_FORMAT.parse(tokens[7]).getTime());
        this.dateOfEmployment = new Date(SIMPLE_DATE_FORMAT.parse(tokens[8]).getTime());
        this.salary = Integer.parseInt(tokens[9]);
    }

    public int getIdNumber() {
        return idNumber;
    }


    public String getNamePrefix() {
        return namePrefix;
    }


    public String getFirstName() {
        return firstName;
    }


    public String getMiddleInitial() {
        return middleInitial;
    }


    public String getLastName() {
        return lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }


    public Date getDateOfEmployment() {
        return dateOfEmployment;
    }

    public void setDateOfEmployment(Date dateOfEmployment) {
        this.dateOfEmployment = dateOfEmployment;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public int hashCode() {
        return this.idNumber;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof Employee e)) {
            return false;
        }

        return this.idNumber == e.idNumber;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "idNumber=" + idNumber +
                ", namePrefix='" + namePrefix + '\'' +
                ", firstName='" + firstName + '\'' +
                ", middleInitial='" + middleInitial + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender='" + gender + '\'' +
                ", email='" + email + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", dateOfEmployment=" + dateOfEmployment +
                ", salary=" + salary +
                '}';
    }
}
