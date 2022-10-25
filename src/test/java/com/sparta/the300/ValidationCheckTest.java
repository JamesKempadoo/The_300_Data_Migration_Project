package com.sparta.the300;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.text.ParseException;

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


}
