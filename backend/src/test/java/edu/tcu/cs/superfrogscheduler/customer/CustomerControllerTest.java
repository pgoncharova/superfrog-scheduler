package edu.tcu.cs.superfrogscheduler.customer;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.tcu.cs.superfrogscheduler.request.Request;
import edu.tcu.cs.superfrogscheduler.system.StatusCode;
import edu.tcu.cs.superfrogscheduler.system.exception.ObjectNotFoundException;
import edu.tcu.cs.superfrogscheduler.utils.dto.CustomerDto;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class CustomerControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    CustomerService customerService;

    List<Customer> customers;

    //@Value("${api.endpoint.base-url}")
    String baseUrl = "/api";



    @BeforeEach
    void setUp() throws Exception {
        Request r1 = new Request();
        r1.setId("123456789");
        r1.setFirstName("John");
        r1.setLastName("Doe");
        r1.setPhoneNumber("(123) 456-7890");
        r1.setEmail("j.doe@tcu.edu");
        r1.setEventType("Private/Residential Event");
        r1.setEventTitle("Birthday Party!");
        r1.setOrganizationName("N/A");
        r1.setEventAddress("2701 S Hulen St, Fort Worth, TX 76109");
        r1.setOnCampus(false);
        r1.setSpecialInstructions("Please park in the parking lot!");
        r1.setBenefitsDescription("Water and pizza will be provided.");
        r1.setSponsorDescription("N/A");
        r1.setDetailedDescription("This event is my son's birthday party.");

        Request r2 = new Request();
        r2.setId("1231231231");
        r2.setFirstName("Elizabeth");
        r2.setLastName("Smith");
        r2.setPhoneNumber("(333) 444-5555");
        r2.setEmail("e.smith@tcu.edu");
        r2.setEventType("TCU Event");
        r2.setEventTitle("TCU E-sports Iron Skillet");
        r2.setOrganizationName("TCU E-sports CLub");
        r2.setEventAddress("3100 McFarlin Blvd, Dallas, TX 75205");
        r2.setOnCampus(false);
        r2.setSpecialInstructions("Free parking for TCU attendees opposite of provided address.");
        r2.setBenefitsDescription("Each attendee will receive a ticket for food - redeemable between 10am and 3pm - at the cafeteria located in the basement of Hughes-Trigg Student Center.");
        r2.setSponsorDescription("SMU E-sports Club will be co-sponsoring this event alongside TCU E-sports Club.");
        r2.setDetailedDescription("The event will be streamed live on https://www.twitch.tv/smu_esports");

        Request r3 = new Request();
        r3.setId("987654321");
        r3.setFirstName("Alice");
        r3.setLastName("Johnson");
        r3.setPhoneNumber("(111) 222-3333");
        r3.setEmail("alice.johnson@example.com");
        r3.setEventType("Corporate Event");
        r3.setEventTitle("Product Launch");
        r3.setOrganizationName("ABC Corporation");
        r3.setEventAddress("123 Main St, Anytown, USA");
        r3.setOnCampus(false);
        r3.setSpecialInstructions("Please use the main entrance.");
        r3.setBenefitsDescription("Attendees will receive complimentary gift bags.");
        r3.setSponsorDescription("N/A");
        r3.setDetailedDescription("We are launching our latest product line to the public.");

        Request r4 = new Request();
        r4.setId("555444333");
        r4.setFirstName("Michael");
        r4.setLastName("Brown");
        r4.setPhoneNumber("(555) 666-7777");
        r4.setEmail("michael.brown@example.com");
        r4.setEventType("Community Event");
        r4.setEventTitle("Charity Fundraiser");
        r4.setOrganizationName("Local Community Center");
        r4.setEventAddress("456 Oak St, Smalltown, USA");
        r4.setOnCampus(false);
        r4.setSpecialInstructions("Please bring your printed ticket for entry.");
        r4.setBenefitsDescription("All proceeds will go towards funding local school programs.");
        r4.setSponsorDescription("Local businesses are generously sponsoring this event.");
        r4.setDetailedDescription("Join us for an evening of fun and fundraising!");

        Request r5 = new Request();
        r5.setId("777888999");
        r5.setFirstName("Sophia");
        r5.setLastName("Martinez");
        r5.setPhoneNumber("(777) 888-9999");
        r5.setEmail("sophia.martinez@example.com");
        r5.setEventType("Educational Event");
        r5.setEventTitle("STEM Workshop");
        r5.setOrganizationName("Science Explorers");
        r5.setEventAddress("789 Elm St, Anytown, USA");
        r5.setOnCampus(false);
        r5.setSpecialInstructions("Parking available in the rear lot.");
        r5.setBenefitsDescription("Participants will receive certificates of completion.");
        r5.setSponsorDescription("N/A");
        r5.setDetailedDescription("Hands-on workshops for kids to explore science, technology, engineering, and math.");

        this.customers = new ArrayList<>();

        Customer c1 = new Customer();
        c1.setId(1L);
        c1.setFirstName("John");
        c1.setLastName("Doe");
        c1.setEmail("john.doe@example.com");
        c1.setPhoneNumber("123-456-7890");
        c1.addRequest(r1);
        c1.addRequest(r2);
        this.customers.add(c1);

        Customer c2 = new Customer();
        c2.setId(2L);
        c2.setFirstName("Jane");
        c2.setLastName("Smith");
        c2.setEmail("jane.smith@example.com");
        c2.setPhoneNumber("987-654-3210");
        c2.addRequest(r3);
        c2.addRequest(r4);
        this.customers.add(c2);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testFindAllSuccess() throws Exception {
        // Given. Arrange inputs and targets. Define the behavior of Mock object wizardService.
        given(this.customerService.findAll()).willReturn(this.customers);

        // When and then
        this.mockMvc.perform(get(this.baseUrl + "/customers").accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.flag").value(true))
                .andExpect(jsonPath("$.code").value(StatusCode.SUCCESS))
                .andExpect(jsonPath("$.message").value("Find All Success"))
                .andExpect(jsonPath("$.data", Matchers.hasSize(this.customers.size())))
                .andExpect(jsonPath("$.data[0].id").value(1L))
                .andExpect(jsonPath("$.data[0].firstName").value("John"))
                .andExpect(jsonPath("$.data[1].id").value(2L))
                .andExpect(jsonPath("$.data[1].firstName").value("Jane"));
    }

    @Test
    void testFindByIdSuccess() throws Exception {
        // Given. Arrange inputs and targets. Define the behavior of Mock object wizardService.
        given(this.customerService.findById(1L)).willReturn(this.customers.get(0));

        // When and then
        this.mockMvc.perform(get(this.baseUrl + "/customers/1").accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.flag").value(true))
                .andExpect(jsonPath("$.code").value(StatusCode.SUCCESS))
                .andExpect(jsonPath("$.message").value("Find One Success"))
                .andExpect(jsonPath("$.data.id").value(1L))
                .andExpect(jsonPath("$.data.firstName").value("John"));
    }

    @Test
    void testFindByIdNotFound() throws Exception {
        // Given. Arrange inputs and targets. Define the behavior of Mock object wizardService.
        given(this.customerService.findById(5L)).willThrow(new ObjectNotFoundException("customer", 5L));

        // When and then
        this.mockMvc.perform(get(this.baseUrl + "/customers/5").accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.flag").value(false))
                .andExpect(jsonPath("$.code").value(StatusCode.NOT_FOUND))
                .andExpect(jsonPath("$.message").value("Could not find customer with id 5."))
                .andExpect(jsonPath("$.data").isEmpty());
    }

    @Test
    void testAddSuccess() throws Exception {
        CustomerDto customerDto = new CustomerDto(3L,
                "John",
                "Doe",
                "john.doe@example.com",
                "123-456-7890",
                0);

        String json = this.objectMapper.writeValueAsString(customerDto);

        Customer savedCustomer = new Customer();
        savedCustomer.setId(3L);
        savedCustomer.setFirstName("Emma");
        savedCustomer.setLastName("Johnson");
        savedCustomer.setEmail("emma.johnson@example.com");
        savedCustomer.setPhoneNumber("(555) 123-4567");

        // Given. Arrange inputs and targets. Define the behavior of Mock object wizardService.
        given(this.customerService.save(Mockito.any(Customer.class))).willReturn(savedCustomer);

        // When and then
        this.mockMvc.perform(post(this.baseUrl + "/customers").contentType(MediaType.APPLICATION_JSON).content(json).accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.flag").value(true))
                .andExpect(jsonPath("$.code").value(StatusCode.SUCCESS))
                .andExpect(jsonPath("$.message").value("Add Success"))
                .andExpect(jsonPath("$.data.id").value(3L))
                .andExpect(jsonPath("$.data.firstName").value("Emma"))
                .andExpect(jsonPath("$.data.lastName").value("Johnson"))
                .andExpect(jsonPath("$.data.email").value("emma.johnson@example.com"))
                .andExpect(jsonPath("$.data.phoneNumber").value("(555) 123-4567"));
    }

    @Test
    void testUpdateSuccess() throws Exception {
        CustomerDto customerDto = new CustomerDto(1L,
                "Updated customer first name",
                "Doe",
                "john.doe@example.com",
                "123-456-7890", 0);

        Customer updatedCustomer = new Customer();
        updatedCustomer.setId(1L);
        updatedCustomer.setFirstName("Updated customer first name");
        updatedCustomer.setLastName("Doe");
        updatedCustomer.setEmail("john.doe@example.com");
        updatedCustomer.setPhoneNumber("123-456-7890");

        String json = this.objectMapper.writeValueAsString(updatedCustomer);

        // Given. Arrange inputs and targets. Define the behavior of Mock object wizardService.
        given(this.customerService.update(eq(1L), Mockito.any(Customer.class))).willReturn(updatedCustomer);

        // When and then
        this.mockMvc.perform(put(this.baseUrl + "/customers/1").contentType(MediaType.APPLICATION_JSON).content(json).accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.flag").value(true))
                .andExpect(jsonPath("$.code").value(StatusCode.SUCCESS))
                .andExpect(jsonPath("$.message").value("Update Success"))
                .andExpect(jsonPath("$.data.id").value(1L))
                .andExpect(jsonPath("$.data.firstName").value("Updated customer first name"))
                .andExpect(jsonPath("$.data.lastName").value("Doe"))
                .andExpect(jsonPath("$.data.email").value("john.doe@example.com"))
                .andExpect(jsonPath("$.data.phoneNumber").value("123-456-7890"));
    }

    @Test
    void testUpdateErrorWithNonExistentId() throws Exception {
        // Given. Arrange inputs and targets. Define the behavior of Mock object wizardService.
        given(this.customerService.update(eq(5L), Mockito.any(Customer.class))).willThrow(new ObjectNotFoundException("customer", 5L));

        CustomerDto customerDto = new CustomerDto(5L, // This id does not exist in the database.
                "Updated customer first name",
                "Doe",
                "john.doe@example.com",
                "123-456-7890", 0);

        String json = this.objectMapper.writeValueAsString(customerDto);

        // When and then
        this.mockMvc.perform(put(this.baseUrl + "/customers/5").contentType(MediaType.APPLICATION_JSON).content(json).accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.flag").value(false))
                .andExpect(jsonPath("$.code").value(StatusCode.NOT_FOUND))
                .andExpect(jsonPath("$.message").value("Could not find customer with id 5."))
                .andExpect(jsonPath("$.data").isEmpty());
    }

    @Test
    void testDeleteSuccess() throws Exception {
        // Given. Arrange inputs and targets. Define the behavior of Mock object wizardService.
        doNothing().when(this.customerService).delete(2L);

        // When and then
        this.mockMvc.perform(delete(this.baseUrl + "/customers/2").accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.flag").value(true))
                .andExpect(jsonPath("$.code").value(StatusCode.SUCCESS))
                .andExpect(jsonPath("$.message").value("Delete Success"))
                .andExpect(jsonPath("$.data").isEmpty());
    }

    @Test
    void testDeleteErrorWithNonExistentId() throws Exception {
        // Given. Arrange inputs and targets. Define the behavior of Mock object wizardService.
        doThrow(new ObjectNotFoundException("customer", 5L)).when(this.customerService).delete(5L);

        // When and then
        this.mockMvc.perform(delete(this.baseUrl + "/customers/5").accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.flag").value(false))
                .andExpect(jsonPath("$.code").value(StatusCode.NOT_FOUND))
                .andExpect(jsonPath("$.message").value("Could not find customer with id 5."))
                .andExpect(jsonPath("$.data").isEmpty());
    }

    @Test
    void testAssignRequestSuccess() throws Exception {
        // Given
        doNothing().when(this.customerService).assignRequest(2L, "777888999");

        // When and then
        this.mockMvc.perform(put(this.baseUrl + "/customers/2/requests/777888999").accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.flag").value(true))
                .andExpect(jsonPath("$.code").value(StatusCode.SUCCESS))
                .andExpect(jsonPath("$.message").value("Request Assignment Success"))
                .andExpect(jsonPath("$.data").isEmpty());
    }

    @Test
    void testAssignRequestErrorWithNonExistentCustomerId() throws Exception {
        // Given
        doThrow(new ObjectNotFoundException("customer", 5L)).when(this.customerService).assignRequest(5L, "777888999");

        // When and then
        this.mockMvc.perform(put(this.baseUrl + "/customers/5/requests/777888999").accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.flag").value(false))
                .andExpect(jsonPath("$.code").value(StatusCode.NOT_FOUND))
                .andExpect(jsonPath("$.message").value("Could not find customer with id 5."))
                .andExpect(jsonPath("$.data").isEmpty());
    }

    @Test
    void testAssignRequestErrorWithNonExistentRequestId() throws Exception {
        // Given
        doThrow(new ObjectNotFoundException("request", "111")).when(this.customerService).assignRequest(2L, "111");

        // When and then
        this.mockMvc.perform(put(this.baseUrl + "/customers/2/requests/111").accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.flag").value(false))
                .andExpect(jsonPath("$.code").value(StatusCode.NOT_FOUND))
                .andExpect(jsonPath("$.message").value("Could not find request with id 111."))
                .andExpect(jsonPath("$.data").isEmpty());
    }
}














