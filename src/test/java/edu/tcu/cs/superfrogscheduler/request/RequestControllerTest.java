package edu.tcu.cs.superfrogscheduler.request;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class RequestControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    RequestService requestService;

    List<Request> requests;

    @BeforeEach
    void setUp() {
        this.requests = new ArrayList<>();

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
        this.requests.add(r1);



    }

    @Test
    void findRequestById() {
    }

    @Test
    void findAllRequests() {
    }

    @Test
    void addRequest() {
    }

    @Test
    void updateRequest() {
    }

    @Test
    void deleteRequest() {
    }
}