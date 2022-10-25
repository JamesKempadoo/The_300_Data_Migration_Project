package com.sparta.the300;

import java.sql.Date;

public class ValidationCheck {

    public static boolean isEmployeeValid(Employee employee) {

        return true;
    }

    public static boolean isDateWithinRange(Date date) {
        return date.getTime() <= System.currentTimeMillis();
    }

    public static boolean isEmploymentDateAfterBirthDate(Date birthDate, Date employmentDate) {
        return birthDate.getTime() < employmentDate.getTime();
    }
}
