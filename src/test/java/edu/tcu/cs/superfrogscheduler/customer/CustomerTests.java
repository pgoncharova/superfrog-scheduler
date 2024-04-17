package edu.tcu.cs.superfrogscheduler.customer;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

@SpringBootTest
public class CustomerTests {

    private Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testCustomerCreation() {
        Customer customer = new Customer(1L, "John", "Doe", "john.doe@example.com", "+1234567890");
        assertEquals("John", customer.getFirstName());
        assertEquals("Doe", customer.getLastName());
        assertEquals("john.doe@example.com", customer.getEmail());
        assertEquals("+1234567890", customer.getPhoneNumber());
    }

    @Test
    public void testCustomerValidationSuccess() {
        Customer customer = new Customer(1L, "Jane", "Smith", "jane.smith@example.com", "+1987654321");
        var constraintViolations = validator.validate(customer);
        assertTrue(constraintViolations.isEmpty());
    }

    @Test
    public void testInvalidEmail() {
        Customer customer = new Customer(1L, "Alice", "Johnson", "not-an-email", "+1234567890");
        var constraintViolations = validator.validate(customer);
        assertFalse(constraintViolations.isEmpty());
        assertEquals("Email should be valid", constraintViolations.iterator().next().getMessage());
    }

    @Test
    public void testInvalidPhoneNumber() {
        Customer customer = new Customer(1L, "Bob", "Lee", "bob.lee@example.com", "1234");
        var constraintViolations = validator.validate(customer);
        assertFalse(constraintViolations.isEmpty());
        assertEquals("Phone number is invalid", constraintViolations.iterator().next().getMessage());
    }
}
