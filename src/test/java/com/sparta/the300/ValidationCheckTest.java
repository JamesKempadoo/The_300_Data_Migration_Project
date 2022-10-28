package com.sparta.the300;

import com.sparta.the300.model.Employee;
import com.sparta.the300.model.ValidationCheck;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class ValidationCheckTest {

    @Test
    @DisplayName("Check negative salary returns FALSE")
    void checkNegativeSalary(){
        try {
            Employee e = new Employee(new String[] {"198429","Mrs.","Serafina","I","Bumgarner","F","serafina.bumgarner@exxonmobil.com",
                    "9/21/1982","02/01/2008","-69294"});

            Assertions.assertFalse(ValidationCheck.isCorrectSalary(e.getSalary()));
        } catch (ParseException ex) {
            throw new RuntimeException(ex);
        }

    }

    @Test
    @DisplayName("Check positive salary returns TRUE")
    void checkPositiveSalary(){
        try {
            Employee e = new Employee(new String[] {"198429","Mrs.","Serafina","I","Bumgarner","F","serafina.bumgarner@exxonmobil.com",
                    "9/21/1982","02/01/2008","69294"});

            Assertions.assertTrue(ValidationCheck.isCorrectSalary(e.getSalary()));
        } catch (ParseException ex) {
            throw new RuntimeException(ex);
        }

    }

    @Test
    @DisplayName("Check correct middle name returns TRUE")
    void checkCorrectMiddleName(){
        try {
            Employee e = new Employee(new String[] {"198429","Mrs.","Serafina","I","Bumgarner","F","serafina.bumgarner@exxonmobil.com",
                    "9/21/1982","02/01/2008","69294"});

            Assertions.assertTrue(ValidationCheck.hasMiddleName(e.getMiddleInitial()));
        } catch (ParseException ex) {
            throw new RuntimeException(ex);
        }

    }

    @Test
    @DisplayName("Check false middle name returns FALSE")
    void checkFalseMiddleName(){
        try {
            Employee e = new Employee(new String[] {"198429","Mrs.","Serafina","FALSE","Bumgarner","F","serafina" +
                    ".bumgarner@exxonmobil.com",
                    "9/21/1982","02/01/2008","69294"});

            Assertions.assertFalse(ValidationCheck.hasMiddleName(e.getMiddleInitial()));
        } catch (ParseException ex) {
            throw new RuntimeException(ex);
        }

    }

    @Test
    @DisplayName("Check correct gender returns True")
    void checkIsCorrectGender(){
        try {
            Employee e = new Employee(new String[] {"198429","Mrs.","Serafina","FALSE","Bumgarner","F","serafina" +
                    ".bumgarner@exxonmobil.com",
                    "9/21/1982","02/01/2008","69294"});
            Employee e1 = new Employee(new String[] {"198429","Mrs.","Serafina","FALSE","Bumgarner","M","serafina" +
                    ".bumgarner@exxonmobil.com",
                    "9/21/1982","02/01/2008","69294"});
            Assertions.assertTrue(ValidationCheck.isCorrectGender(e.getGender()));
            Assertions.assertTrue(ValidationCheck.isCorrectGender(e1.getGender()));
        } catch (ParseException ex) {
            throw new RuntimeException(ex);
        }

    }

    @Test
    @DisplayName("Check incorrect gender returns False")
    void checkIsIncorrectGender(){
        try {
            Employee e = new Employee(new String[] {"198429","Mrs.","Serafina","FALSE","Bumgarner","X","serafina" +
                    ".bumgarner@exxonmobil.com",
                    "9/21/1982","02/01/2008","69294"});
            Employee e1 = new Employee(new String[] {"198429","Mrs.","Serafina","FALSE","Bumgarner","P","serafina" +
                    ".bumgarner@exxonmobil.com",
                    "9/21/1982","02/01/2008","69294"});
            Assertions.assertFalse(ValidationCheck.isCorrectGender(e.getGender()));
            Assertions.assertFalse(ValidationCheck.isCorrectGender(e1.getGender()));
        } catch (ParseException ex) {
            throw new RuntimeException(ex);
        }

    }

    @Test
    @DisplayName("Date is not in the future ")
        void checkDateIsNotInTheFuture(){
        try {
            Employee e = new Employee(new String[] {"198429","Mrs.","Serafina","FALSE","Bumgarner","X","serafina" +
                    ".bumgarner@exxonmobil.com",
                    "9/21/1982","02/01/2008","69294"});

            Assertions.assertTrue(ValidationCheck.isDateWithinRange(e.getDateOfBirth()));
            Assertions.assertTrue(ValidationCheck.isDateWithinRange(e.getDateOfEmployment()));
        } catch (ParseException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Test
    @DisplayName("Date is in the future ")
    void checkDateIsFuture(){
        try {
            Employee e = new Employee(new String[] {"198429","Mrs.","Serafina","FALSE","Bumgarner","X","serafina" +
                    ".bumgarner@exxonmobil.com",
                    "9/21/2082","02/01/2048","69294"});

            Assertions.assertFalse(ValidationCheck.isDateWithinRange(e.getDateOfBirth()));
            Assertions.assertFalse(ValidationCheck.isDateWithinRange(e.getDateOfEmployment()));
        } catch (ParseException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Test
    @DisplayName("Employment date is after birthdate")
    void checkEmploymentDateIsAfterBirthdate(){
        try {
            Employee e = new Employee(new String[] {"198429","Mrs.","Serafina","FALSE","Bumgarner","X","serafina" +
                    ".bumgarner@exxonmobil.com",
                    "9/21/1982","02/01/2008","69294"});

            Assertions.assertTrue(ValidationCheck.isEmploymentDateAfterBirthDate(e.getDateOfBirth(), e.getDateOfEmployment()));
        } catch (ParseException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Test
    @DisplayName("Employment date is before birthdate")
    void checkEmploymentDateIsBeforeBirthdate(){
        try {
            Employee e = new Employee(new String[] {"198429","Mrs.","Serafina","FALSE","Bumgarner","X","serafina" +
                    ".bumgarner@exxonmobil.com",
                    "9/21/2982","02/01/2008","69294"});

            Assertions.assertFalse(ValidationCheck.isEmploymentDateAfterBirthDate(e.getDateOfBirth(), e.getDateOfEmployment()));
        } catch (ParseException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Test
    @DisplayName("Employee data is valid")
    void checkEmployeeDataIsValid(){
        try {
            Employee e = new Employee(new String[] {"198429","Mrs.","Serafina","M","Bumgarner","F","serafina" +
                    ".bumgarner@exxonmobil.com",
                    "9/21/1982","02/01/2008","69294"});

            Assertions.assertTrue(ValidationCheck.isEmployeeValid(e));
        } catch (ParseException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Test
    @DisplayName("Employee data is not valid")
    void checkEmployeeDataIsNotValid(){
        try {
            Employee e = new Employee(new String[] {"198429","Mrs.","Serafina","FALSE","Bumgarner","F","serafina" +
                    ".bumgarner@exxonmobil.com",
                    "9/21/1982","02/01/2008","69294"});
            Employee e1 = new Employee(new String[] {"198429","Mrs.","Serafina","M","Bumgarner","F","serafina" +
                    ".bumgarner@exxonmobil.com",
                    "9/21/1982","02/01/1908","69294"});
            Employee e2 = new Employee(new String[] {"198429","Mrs.","Serafina","FALSE","Bumgarner","X","serafina" +
                    ".bumgarner@exxonmobil.com",
                    "9/21/1982","02/01/2008","69294"});
            Employee e3 = new Employee(new String[] {"198429","Mrs.","Serafina","FALSE","Bumgarner","F","serafina" +
                    ".bumgarner@exxonmobil.com",
                    "9/21/1982","02/01/2008","69294"});
            Employee e4 = new Employee(new String[] {"198429","Mrs.","Serafina","FALSE","Bumgarner","F","serafina" +
                    ".bumgarner@exxonmobil.com",
                    "9/21/1982","02/01/2008","-69294"});
            Assertions.assertFalse(ValidationCheck.isEmployeeValid(e));
            Assertions.assertFalse(ValidationCheck.isEmployeeValid(e1));
            Assertions.assertFalse(ValidationCheck.isEmployeeValid(e2));
            Assertions.assertFalse(ValidationCheck.isEmployeeValid(e3));
            Assertions.assertFalse(ValidationCheck.isEmployeeValid(e4));
        } catch (ParseException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Test
    @DisplayName("EmployeeID not duplicated")
    void checkEmployeeIdNotDuplicate(){
        try {
            Employee e = new Employee(new String[] {"198429","Mrs.","Serafina","M","Bumgarner","F","serafina" +
                    ".bumgarner@exxonmobil.com",
                    "9/21/1982","02/01/2008","69294"});
            Employee e1 = new Employee(new String[] {"398429","Mrs.","Serafina","M","Bumgarner","F","serafina" +
                    ".bumgarner@exxonmobil.com",
                    "9/21/1982","02/01/2008","69294"});
            Employee [] Employees = {e, e1} ;
            HashSet<Employee> Duplication = new HashSet<>();
            for (int k=0 ; k<Employees.length; k++) {
                Assertions.assertTrue(Duplication.add(Employees[k]));
            }
        } catch (ParseException ex) {
            throw new RuntimeException(ex);
        }
    }
    @Test
    @DisplayName("EmployeeID duplicated")
    void checkEmployeeIdDuplicate(){
        try {
            Employee e = new Employee(new String[] {"198429","Mrs.","Serafina","M","Bumgarner","F","serafina" +
                    ".bumgarner@exxonmobil.com",
                    "9/21/1982","02/01/2008","69294"});
            Employee e1 = new Employee(new String[] {"198429","Mrs.","Serafina","M","Bumgarner","F","serafina" +
                    ".bumgarner@exxonmobil.com",
                    "9/21/1982","02/01/2008","69294"});
            Employee [] Employees = {e, e1} ;
            HashSet<Employee> Duplication = new HashSet<>();
                Assertions.assertTrue(Duplication.add(Employees[0]));
                Assertions.assertFalse(Duplication.add(Employees[1]));


        } catch (ParseException ex) {
            throw new RuntimeException(ex);
        }
    }

}



