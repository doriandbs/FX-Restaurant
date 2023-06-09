package models;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import models.administration.Employee;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {

    @Test
    void testConstructor() {
        Employee actualEmployee = new Employee(1, "Name", "Jane", "Badge", "Adresse", "2020-03-01", "Num Tel", "2020-03-01",
                true);

        assertEquals("Adresse", actualEmployee.getAdresse());
        assertEquals("Num Tel", actualEmployee.getNumTel());
        assertEquals("Name", actualEmployee.getName());
        assertEquals("Badge", actualEmployee.getBadge());
        assertEquals("2020-03-01", actualEmployee.getDateEmbauche());
        assertEquals("Jane", actualEmployee.getFirstName());
        assertEquals("2020-03-01", actualEmployee.getDateBirth());
        assertEquals(1, actualEmployee.getId());
        assertTrue(actualEmployee.getIsAdmin());
        BooleanProperty isAdminPropertyResult = actualEmployee.isAdminProperty();
        assertNull(isAdminPropertyResult.getBean());
        assertFalse(isAdminPropertyResult.isBound());
        assertTrue(isAdminPropertyResult.get());
        assertEquals("", isAdminPropertyResult.getName());
    }


    @Test
    void testGetName() {
        assertEquals("Name",
                (new Employee(1, "Name", "Jane", "Badge", "Adresse", "2020-03-01", "Num Tel", "2020-03-01", true)).getName());
    }


    @Test
    void testSetName() {
        Employee employee = new Employee(1, "Name", "Jane", "Badge", "Adresse", "2020-03-01", "Num Tel", "2020-03-01",
                true);
        employee.setName("Name");
        assertEquals("Adresse", employee.getAdresse());
        assertEquals("Num Tel", employee.getNumTel());
        assertEquals("Name", employee.getName());
        assertEquals("Badge", employee.getBadge());
        assertEquals("2020-03-01", employee.getDateEmbauche());
        assertEquals("Jane", employee.getFirstName());
        assertEquals("2020-03-01", employee.getDateBirth());
        assertEquals(1, employee.getId());
        assertTrue(employee.getIsAdmin());
    }


    @Test
    void testNameProperty() {
        assertTrue((new Employee(1, "Name", "Jane", "Badge", "Adresse", "2020-03-01", "Num Tel", "2020-03-01", true))
                .nameProperty() instanceof SimpleStringProperty);
    }


    @Test
    void testGetFirstName() {
        assertEquals("Jane",
                (new Employee(1, "Name", "Jane", "Badge", "Adresse", "2020-03-01", "Num Tel", "2020-03-01", true))
                        .getFirstName());
    }


    @Test
    void testSetFirstName() {
        Employee employee = new Employee(1, "Name", "Jane", "Badge", "Adresse", "2020-03-01", "Num Tel", "2020-03-01",
                true);
        employee.setFirstName("Jane");
        assertEquals("Adresse", employee.getAdresse());
        assertEquals("Num Tel", employee.getNumTel());
        assertEquals("Name", employee.getName());
        assertEquals("Badge", employee.getBadge());
        assertEquals("2020-03-01", employee.getDateEmbauche());
        assertEquals("Jane", employee.getFirstName());
        assertEquals("2020-03-01", employee.getDateBirth());
        assertEquals(1, employee.getId());
        assertTrue(employee.getIsAdmin());
    }


    @Test
    void testFirstNameProperty() {
        assertTrue((new Employee(1, "Name", "Jane", "Badge", "Adresse", "2020-03-01", "Num Tel", "2020-03-01", true))
                .firstNameProperty() instanceof SimpleStringProperty);
    }


    @Test
    void testGetDateBirth() {
        assertEquals("2020-03-01",
                (new Employee(1, "Name", "Jane", "Badge", "Adresse", "2020-03-01", "Num Tel", "2020-03-01", true))
                        .getDateBirth());
    }


    @Test
    void testSetDateBirth() {
        Employee employee = new Employee(1, "Name", "Jane", "Badge", "Adresse", "2020-03-01", "Num Tel", "2020-03-01",
                true);
        employee.setDateBirth("2020-03-01");
        assertEquals("Adresse", employee.getAdresse());
        assertEquals("Num Tel", employee.getNumTel());
        assertEquals("Name", employee.getName());
        assertEquals("Badge", employee.getBadge());
        assertEquals("2020-03-01", employee.getDateEmbauche());
        assertEquals("Jane", employee.getFirstName());
        assertEquals("2020-03-01", employee.getDateBirth());
        assertEquals(1, employee.getId());
        assertTrue(employee.getIsAdmin());
    }


    @Test
    void testDateBirthProperty() {
        assertTrue((new Employee(1, "Name", "Jane", "Badge", "Adresse", "2020-03-01", "Num Tel", "2020-03-01", true))
                .dateBirthProperty() instanceof SimpleStringProperty);
    }


    @Test
    void testGetAdresse() {
        assertEquals("Adresse",
                (new Employee(1, "Name", "Jane", "Badge", "Adresse", "2020-03-01", "Num Tel", "2020-03-01", true))
                        .getAdresse());
    }


    @Test
    void testSetAdresse() {
        Employee employee = new Employee(1, "Name", "Jane", "Badge", "Adresse", "2020-03-01", "Num Tel", "2020-03-01",
                true);
        employee.setAdresse("Adresse");
        assertEquals("Adresse", employee.getAdresse());
        assertEquals("Num Tel", employee.getNumTel());
        assertEquals("Name", employee.getName());
        assertEquals("Badge", employee.getBadge());
        assertEquals("2020-03-01", employee.getDateEmbauche());
        assertEquals("Jane", employee.getFirstName());
        assertEquals("2020-03-01", employee.getDateBirth());
        assertEquals(1, employee.getId());
        assertTrue(employee.getIsAdmin());
    }


    @Test
    void testAdresseProperty() {
        assertTrue((new Employee(1, "Name", "Jane", "Badge", "Adresse", "2020-03-01", "Num Tel", "2020-03-01", true))
                .adresseProperty() instanceof SimpleStringProperty);
    }


    @Test
    void testGetNumTel() {
        assertEquals("Num Tel",
                (new Employee(1, "Name", "Jane", "Badge", "Adresse", "2020-03-01", "Num Tel", "2020-03-01", true))
                        .getNumTel());
    }


    @Test
    void testSetNumTel() {
        Employee employee = new Employee(1, "Name", "Jane", "Badge", "Adresse", "2020-03-01", "Num Tel", "2020-03-01",
                true);
        employee.setNumTel("Num Tel");
        assertEquals("Adresse", employee.getAdresse());
        assertEquals("Num Tel", employee.getNumTel());
        assertEquals("Name", employee.getName());
        assertEquals("Badge", employee.getBadge());
        assertEquals("2020-03-01", employee.getDateEmbauche());
        assertEquals("Jane", employee.getFirstName());
        assertEquals("2020-03-01", employee.getDateBirth());
        assertEquals(1, employee.getId());
        assertTrue(employee.getIsAdmin());
    }


    @Test
    void testNumTelProperty() {
        assertTrue((new Employee(1, "Name", "Jane", "Badge", "Adresse", "2020-03-01", "Num Tel", "2020-03-01", true))
                .numTelProperty() instanceof SimpleStringProperty);
    }


    @Test
    void testGetDateEmbauche() {
        assertEquals("2020-03-01",
                (new Employee(1, "Name", "Jane", "Badge", "Adresse", "2020-03-01", "Num Tel", "2020-03-01", true))
                        .getDateEmbauche());
    }


    @Test
    void testSetDateEmbauche() {
        Employee employee = new Employee(1, "Name", "Jane", "Badge", "Adresse", "2020-03-01", "Num Tel", "2020-03-01",
                true);
        employee.setDateEmbauche("2020-03-01");
        assertEquals("Adresse", employee.getAdresse());
        assertEquals("Num Tel", employee.getNumTel());
        assertEquals("Name", employee.getName());
        assertEquals("Badge", employee.getBadge());
        assertEquals("2020-03-01", employee.getDateEmbauche());
        assertEquals("Jane", employee.getFirstName());
        assertEquals("2020-03-01", employee.getDateBirth());
        assertEquals(1, employee.getId());
        assertTrue(employee.getIsAdmin());
    }

    @Test
    void testDateEmbaucheProperty() {
        assertTrue((new Employee(1, "Name", "Jane", "Badge", "Adresse", "2020-03-01", "Num Tel", "2020-03-01", true))
                .dateEmbaucheProperty() instanceof SimpleStringProperty);
    }


    @Test
    void testGetBadge() {
        assertEquals("Badge",
                (new Employee(1, "Name", "Jane", "Badge", "Adresse", "2020-03-01", "Num Tel", "2020-03-01", true))
                        .getBadge());
    }


    @Test
    void testSetBadge() {
        Employee employee = new Employee(1, "Name", "Jane", "Badge", "Adresse", "2020-03-01", "Num Tel", "2020-03-01",
                true);
        employee.setBadge("Badge");
        assertEquals("Adresse", employee.getAdresse());
        assertEquals("Num Tel", employee.getNumTel());
        assertEquals("Name", employee.getName());
        assertEquals("Badge", employee.getBadge());
        assertEquals("2020-03-01", employee.getDateEmbauche());
        assertEquals("Jane", employee.getFirstName());
        assertEquals("2020-03-01", employee.getDateBirth());
        assertEquals(1, employee.getId());
        assertTrue(employee.getIsAdmin());
    }


    @Test
    void testBadgeProperty() {
        assertTrue((new Employee(1, "Name", "Jane", "Badge", "Adresse", "2020-03-01", "Num Tel", "2020-03-01", true))
                .badgeProperty() instanceof SimpleStringProperty);
    }


    @Test
    void testGetIsAdmin() {
        assertTrue((new Employee(1, "Name", "Jane", "Badge", "Adresse", "2020-03-01", "Num Tel", "2020-03-01", true))
                .getIsAdmin());
        assertFalse((new Employee(1, "Name", "Jane", "Badge", "Adresse", "2020-03-01", "Num Tel", "2020-03-01", false))
                .getIsAdmin());
    }

    @Test
    void testSetIsAdmin() {
        Employee employee = new Employee(1, "Name", "Jane", "Badge", "Adresse", "2020-03-01", "Num Tel", "2020-03-01",
                true);
        employee.setIsAdmin(true);
        assertEquals("Adresse", employee.getAdresse());
        assertEquals("Num Tel", employee.getNumTel());
        assertEquals("Name", employee.getName());
        assertEquals("Badge", employee.getBadge());
        assertEquals("2020-03-01", employee.getDateEmbauche());
        assertEquals("Jane", employee.getFirstName());
        assertEquals("2020-03-01", employee.getDateBirth());
        assertEquals(1, employee.getId());
        assertTrue(employee.getIsAdmin());
    }




    @Test
    void testGetId() {
        assertEquals(1,
                (new Employee(1, "Name", "Jane", "Badge", "Adresse", "2020-03-01", "Num Tel", "2020-03-01", true)).getId());
    }


    @Test
    void testSetId() {
        Employee employee = new Employee(1, "Name", "Jane", "Badge", "Adresse", "2020-03-01", "Num Tel", "2020-03-01",
                true);
        employee.setId(1);
        assertEquals("Adresse", employee.getAdresse());
        assertEquals("Num Tel", employee.getNumTel());
        assertEquals("Name", employee.getName());
        assertEquals("Badge", employee.getBadge());
        assertEquals("2020-03-01", employee.getDateEmbauche());
        assertEquals("Jane", employee.getFirstName());
        assertEquals("2020-03-01", employee.getDateBirth());
        assertEquals(1, employee.getId());
        assertTrue(employee.getIsAdmin());
    }

    @Test
    void testIdProperty() {
        assertTrue((new Employee(1, "Name", "Jane", "Badge", "Adresse", "2020-03-01", "Num Tel", "2020-03-01", true))
                .idProperty() instanceof SimpleIntegerProperty);
    }
}

