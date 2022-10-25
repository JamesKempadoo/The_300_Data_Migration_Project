package com.sparta.the300;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.text.ParseException;

public class EmployeeTest {

    @Test
    @DisplayName("Check whether two employees with same id are equal")
    void checkEmployeesWithSameID() {
        try {
            Employee e = new Employee(new String[] {"198429","Mrs.","Serafina","I","Bumgarner","F","serafina.bumgarner@exxonmobil.com",
                    "9/21/1982","02/01/2008","69294"});
            Employee e1 = new Employee(new String[] {"198429","Mrs.","Juliette","M","Rojo","F","juliette.rojo@yahoo.co.uk","05/08/1967",
                    "06/04/2011","193912"});
            Assertions.assertEquals(e, e1);
        } catch (ParseException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Test
    @DisplayName("Check whether two employees with different id returns false")
    void checkEmployeesWithDifferentID() {
        try {
            Employee e = new Employee(new String[] {"198429","Mrs.","Serafina","I","Bumgarner","F","serafina.bumgarner@exxonmobil.com",
                    "9/21/1982","02/01/2008","69294"});
            Employee e1 = new Employee(new String[] {"214678","Mrs.","Juliette","M","Rojo","F","juliette.rojo@yahoo.co.uk","05/08/1967",
                    "06/04/2011","193912"});
            Assertions.assertNotEquals(e, e1);
        } catch (ParseException ex) {
            throw new RuntimeException(ex);
        }
    }
    @Test
    @DisplayName("Check whether two employees with different id but any other member variable equal return false")
    void checkEmployeesWithSameMembersExceptID() {
        try {
            Employee e = new Employee(new String[] {"198429","Mrs.","Serafina","I","Bumgarner","F","serafina.bumgarner@exxonmobil.com",
                    "9/21/1982","02/01/2008","69294"});
            Employee e1 = new Employee(new String[] {"214678","Mrs.","Serafina","I","Bumgarner","F","serafina.bumgarner@exxonmobil.com",
                    "9/21/1982","02/01/2008","69294"});
            Assertions.assertNotEquals(e, e1);
        } catch (ParseException ex) {
            throw new RuntimeException(ex);
        }
    }
}
