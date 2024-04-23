package edu.tcu.cs.superfrogscheduler.customer;

import edu.tcu.cs.superfrogscheduler.request.Request;
import edu.tcu.cs.superfrogscheduler.request.RequestRepository;
import edu.tcu.cs.superfrogscheduler.system.exception.ObjectNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @Mock
    CustomerRepository customerRepository;

    @Mock
    RequestRepository requestRepository;

    @InjectMocks
    CustomerService customerService;

    List<Customer> customers;

    @BeforeEach
    void setUp() {
        Customer c1 = new Customer();
        c1.setId(1L);
        c1.setFirstName("John");
        c1.setLastName("Doe");
        c1.setEmail("john.doe@example.com");
        c1.setPhoneNumber("123-456-7890");

        Customer c2 = new Customer();
        c2.setId(2L);
        c2.setFirstName("Jane");
        c2.setLastName("Smith");
        c2.setEmail("jane.smith@example.com");
        c2.setPhoneNumber("987-654-3210");

        this.customers = new ArrayList<>();
        this.customers.add(c1);
        this.customers.add(c2);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testFindAllSuccess() {
        // Given. Arrange inputs and targets. Define the behavior of Mock object wizardRepository.
        given(this.customerRepository.findAll()).willReturn(this.customers);

        // When. Act on the target behavior. Act steps should cover the method to be tested.
        List<Customer> actualCustomers = this.customerService.findAll();

        // Then. Assert expected outcomes.
        assertThat(actualCustomers.size()).isEqualTo(this.customers.size());

        // Verify wizardRepository.findAll() is called exactly once.
        verify(this.customerRepository, times(1)).findAll();
    }

    @Test
    void testFindByIdSuccess() {
        // Given. Arrange inputs and targets. Define the behavior of Mock object wizardRepository.
        Customer c = new Customer();
        c.setId(1L);
        c.setFirstName("John");
        c.setLastName("Doe");
        c.setEmail("john.doe@example.com");
        c.setPhoneNumber("123-456-7890");

        given(this.customerRepository.findById(1L)).willReturn(Optional.of(c)); // Define the behavior of the mock object.

        // When. Act on the target behavior. Act steps should cover the method to be tested.
        Customer returnedCustomer = this.customerService.findById(1L);

        // Then. Assert expected outcomes.
        assertThat(returnedCustomer.getId()).isEqualTo(c.getId());
        assertThat(returnedCustomer.getFirstName()).isEqualTo(c.getFirstName());
        assertThat(returnedCustomer.getLastName()).isEqualTo(c.getLastName());
        assertThat(returnedCustomer.getEmail()).isEqualTo(c.getEmail());
        assertThat(returnedCustomer.getPhoneNumber()).isEqualTo(c.getPhoneNumber());
        verify(this.customerRepository, times(1)).findById(1L);
    }

    @Test
    void testFindByIdNotFound() {
        // Given
        given(this.customerRepository.findById(Mockito.any(Long.class))).willReturn(Optional.empty());

        // When
        Throwable thrown = catchThrowable(() -> {
            Customer returnedCustomer = this.customerService.findById(1L);
        });

        // Then
        assertThat(thrown)
                .isInstanceOf(ObjectNotFoundException.class)
                .hasMessage("Could not find customer with id 1.");
        verify(this.customerRepository, times(1)).findById(Mockito.any(Long.class));
    }

    @Test
    void testSaveSuccess() {
        // Given
        Customer newCustomer = new Customer();
        newCustomer.setId(1L);
        newCustomer.setFirstName("John");
        newCustomer.setLastName("Doe");
        newCustomer.setEmail("john.doe@example.com");
        newCustomer.setPhoneNumber("123-456-7890");

        given(this.customerRepository.save(newCustomer)).willReturn(newCustomer);

        // When
        Customer returnedCustomer = this.customerService.save(newCustomer);

        // Then
        assertThat(returnedCustomer.getId()).isEqualTo(newCustomer.getId());
        assertThat(returnedCustomer.getFirstName()).isEqualTo(newCustomer.getFirstName());
        assertThat(returnedCustomer.getLastName()).isEqualTo(newCustomer.getLastName());
        assertThat(returnedCustomer.getEmail()).isEqualTo(newCustomer.getEmail());
        assertThat(returnedCustomer.getPhoneNumber()).isEqualTo(newCustomer.getPhoneNumber());
        verify(this.customerRepository, times(1)).save(newCustomer);
    }

    @Test
    void testUpdateSuccess() {
        // Given
        Customer oldCustomer = new Customer();
        oldCustomer.setId(1L);
        oldCustomer.setFirstName("John");
        oldCustomer.setLastName("Doe");
        oldCustomer.setEmail("john.doe@example.com");
        oldCustomer.setPhoneNumber("123-456-7890");

        Customer update = new Customer();
        update.setFirstName("Johnny");

        given(this.customerRepository.findById(1L)).willReturn(Optional.of(oldCustomer));
        given(this.customerRepository.save(oldCustomer)).willReturn(oldCustomer);

        // When
        Customer updatedCustomer = this.customerService.update(1L, update);

        // Then
        assertThat(updatedCustomer.getId()).isEqualTo(1L);
        assertThat(updatedCustomer.getFirstName()).isEqualTo(update.getFirstName());
        assertThat(updatedCustomer.getLastName()).isEqualTo(update.getLastName());
        assertThat(updatedCustomer.getEmail()).isEqualTo(update.getEmail());
        assertThat(updatedCustomer.getPhoneNumber()).isEqualTo(update.getPhoneNumber());
        verify(this.customerRepository, times(1)).findById(1L);
        verify(this.customerRepository, times(1)).save(oldCustomer);
    }

    @Test
    void testUpdateNotFound() {
        // Given
        Customer update = new Customer();
        update.setFirstName("Johnny");

        given(this.customerRepository.findById(1L)).willReturn(Optional.empty());

        // When
        assertThrows(ObjectNotFoundException.class, () -> {
            this.customerService.update(1L, update);
        });

        // Then
        verify(this.customerRepository, times(1)).findById(1L);
    }

    @Test
    void testDeleteSuccess() {
        // Given
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setFirstName("John");
        customer.setLastName("Doe");
        customer.setEmail("john.doe@example.com");
        customer.setPhoneNumber("123-456-7890");

        given(this.customerRepository.findById(1L)).willReturn(Optional.of(customer));
        doNothing().when(this.customerRepository).deleteById(1L);

        // When
        this.customerService.delete(1L);

        // Then
        verify(this.customerRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteNotFound() {
        // Given
        given(this.customerRepository.findById(1L)).willReturn(Optional.empty());

        // When
        assertThrows(ObjectNotFoundException.class, () -> {
            this.customerService.delete(1L);
        });

        // Then
        verify(this.customerRepository, times(1)).findById(1L);
    }

    @Test
    void testAssignRequestSuccess() {
        // Given
        Request request = new Request();
        request.setId("123456789");
        request.setFirstName("John");
        request.setLastName("Doe");
        request.setPhoneNumber("(123) 456-7890");
        request.setEmail("j.doe@tcu.edu");
        request.setEventType("Private/Residential Event");
        request.setEventTitle("Birthday Party!");
        request.setOrganizationName("N/A");
        request.setEventAddress("2701 S Hulen St, Fort Worth, TX 76109");
        request.setOnCampus(false);
        request.setSpecialInstructions("Please park in the parking lot!");
        request.setBenefitsDescription("Water and pizza will be provided.");
        request.setSponsorDescription("N/A");
        request.setDetailedDescription("This event is my son's birthday party.");

        Customer c1 = new Customer();
        c1.setId(1L);
        c1.setFirstName("John");
        c1.setLastName("Doe");
        c1.setEmail("john.doe@example.com");
        c1.setPhoneNumber("123-456-7890");

        Customer c2 = new Customer();
        c2.setId(2L);
        c2.setFirstName("Jane");
        c2.setLastName("Smith");
        c2.setEmail("jane.smith@example.com");
        c2.setPhoneNumber("987-654-3210");

        given(this.requestRepository.findById("123456789")).willReturn(Optional.of(request));
        given(this.customerRepository.findById(2L)).willReturn(Optional.of(c2));

        // When
        this.customerService.assignRequest(2L, "123456789");

        // Then
        assertThat(request.getOwner().getId()).isEqualTo(2L);
        assertThat(c2.getRequests()).contains(request);
    }

    @Test
    void testAssignRequestErrorWithNonExistentCustomerId() {
        // Given
        Request request = new Request();
        request.setId("123456789");
        request.setFirstName("John");
        request.setLastName("Doe");
        request.setPhoneNumber("(123) 456-7890");
        request.setEmail("j.doe@tcu.edu");
        request.setEventType("Private/Residential Event");
        request.setEventTitle("Birthday Party!");
        request.setOrganizationName("N/A");
        request.setEventAddress("2701 S Hulen St, Fort Worth, TX 76109");
        request.setOnCampus(false);
        request.setSpecialInstructions("Please park in the parking lot!");
        request.setBenefitsDescription("Water and pizza will be provided.");
        request.setSponsorDescription("N/A");
        request.setDetailedDescription("This event is my son's birthday party.");

        Customer c1 = new Customer();
        c1.setId(1L);
        c1.setFirstName("John");
        c1.setLastName("Doe");
        c1.setEmail("john.doe@example.com");
        c1.setPhoneNumber("123-456-7890");
        c1.addRequest(request);

        given(this.requestRepository.findById("123456789")).willReturn(Optional.of(request));
        given(this.customerRepository.findById(3L)).willReturn(Optional.empty());

        // When
        Throwable thrown = assertThrows(ObjectNotFoundException.class, () -> {
            this.customerService.assignRequest(3L, "123456789");
        });

        // Then
        assertThat(thrown)
                .isInstanceOf(ObjectNotFoundException.class)
                .hasMessage("Could not find customer with id 3.");
        assertThat(request.getOwner().getId()).isEqualTo(1L);
    }

    @Test
    void testAssignRequestErrorWithNonExistentRequestId() {
        // Given
        given(this.requestRepository.findById("123456789")).willReturn(Optional.empty());

        // When
        Throwable thrown = assertThrows(ObjectNotFoundException.class, () -> {
            this.customerService.assignRequest(2L, "123456789");
        });

        // Then
        assertThat(thrown)
                .isInstanceOf(ObjectNotFoundException.class)
                .hasMessage("Could not find request with id 123456789.");
    }
}
