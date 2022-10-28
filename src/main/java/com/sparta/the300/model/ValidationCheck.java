package com.sparta.the300.model;

import java.sql.Date;

public class ValidationCheck {

    public static boolean isEmployeeValid(Employee employee) {
        return isDateWithinRange(employee.getDateOfBirth()) && isDateWithinRange(employee.getDateOfEmployment())
                && isEmploymentDateAfterBirthDate(employee.getDateOfBirth(), employee.getDateOfEmployment())
                && isCorrectGender(employee.getGender()) && hasMiddleName(employee.getMiddleInitial())
                && isCorrectSalary(employee.getSalary());
    }

    public static boolean isDateWithinRange(Date date) {
        return date.getTime() <= System.currentTimeMillis();
    }

    public static boolean isEmploymentDateAfterBirthDate(Date birthDate, Date employmentDate) {
        return birthDate.getTime() < employmentDate.getTime();
    }

    public static boolean isCorrectGender(String gender) {
        return (gender.equalsIgnoreCase("F") || gender.equalsIgnoreCase("M"));
    }

    public static boolean hasMiddleName(String middleName) {
        return middleName.length() == 1;
    }

    public static boolean isCorrectSalary(int salary) {
        return !(salary < 0);
    }
}
