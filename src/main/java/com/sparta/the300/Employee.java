package com.sparta.the300;

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

    public void setIdNumber(int idNumber) {
        this.idNumber = idNumber;
    }

    public String getNamePrefix() {
        return namePrefix;
    }

    public void setNamePrefix(String namePrefix) {
        this.namePrefix = namePrefix;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleInitial() {
        return middleInitial;
    }

    public void setMiddleInitial(String middleInitial) {
        this.middleInitial = middleInitial;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
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
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
