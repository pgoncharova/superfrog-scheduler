package edu.tcu.cs.superfrogscheduler.request;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.tcu.cs.superfrogscheduler.system.StatusCode;
import edu.tcu.cs.superfrogscheduler.system.exception.ObjectNotFoundException;
import edu.tcu.cs.superfrogscheduler.utils.dto.RequestDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class RequestControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    RequestService requestService;

    @Autowired
            ObjectMapper objectMapper;

    List<Request> requests;

    @Value("${api.endpoint.base-url}")
    String baseUrl;

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
        this.requests.add(r2);

    }

    @Test
    void testFindRequestByIdSuccess() throws Exception {
        // Given
        given(this.requestService.findById("123456789"))
                .willReturn(this.requests.get(0));
        // When and then
        this.mockMvc.perform(get(this.baseUrl + "/requests/123456789")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.flag")
                        .value(true))
                .andExpect(jsonPath("$.code")
                        .value(StatusCode.SUCCESS))
                .andExpect(jsonPath("$.message")
                        .value("Find One Success"))
                .andExpect(jsonPath("$.data.id")
                        .value("123456789"));
    }

    @Test
    void testFindRequestByIdNotFound() throws Exception {
        // Given
        given(this.requestService.findById("123456789"))
                .willThrow(new ObjectNotFoundException("request", "123456789"));
        // When and then
        this.mockMvc.perform(get(this.baseUrl + "/requests/123456789")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.flag")
                        .value(false))
                .andExpect(jsonPath("$.code")
                        .value(StatusCode.NOT_FOUND))
                .andExpect(jsonPath("$.message")
                        .value("Could not find request with id 123456789."))
                .andExpect(jsonPath("$.data")
                        .isEmpty());
    }

    @Test
    void testFindAllRequestsSuccess() throws Exception {
        // Given
        given(this.requestService.findAll()).willReturn(this.requests);
        // When and then
        this.mockMvc.perform(get(this.baseUrl + "/requests")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.flag")
                        .value(true))
                .andExpect(jsonPath("$.code")
                        .value(StatusCode.SUCCESS))
                .andExpect(jsonPath("$.message")
                        .value("Find All Success"))
                .andExpect(jsonPath("$.data", Matchers.hasSize(this.requests.size())))
                .andExpect(jsonPath("$.data[0].id")
                        .value("123456789"))
                .andExpect(jsonPath("$.data[0].phoneNumber")
                        .value("(123) 456-7890"))
                .andExpect(jsonPath("$.data[1].id")
                        .value("1231231231"))
                .andExpect(jsonPath("$.data[1].phoneNumber")
                        .value("(333) 444-5555"));
    }

    @Test
    void testAddRequest() throws Exception {
        // Given
        RequestDto requestDto = new RequestDto(null,
                "Sam",
                "McDonald",
                "(555) 666-6565",
                "s.donald@gmail.com",
                "Public School/Non-Profit Event",
                "Endangered Animal Awareness Event",
                "TCU Environmental Club",
                "2950 W Bowie St, Fort Worth, TX 76109",
                true,
                "N/A",
                "Allergy possibility with dogs.",
                "TCU",
                "We will be educating our students on endangered animals.");
        String json = this.objectMapper.writeValueAsString(requestDto);

        Request savedRequest = new Request();
        savedRequest.setId("334433443344");
        savedRequest.setFirstName("Sam");
        savedRequest.setLastName("McDonald");
        savedRequest.setPhoneNumber("(555) 666-6565");
        savedRequest.setEmail("s.donald@gmail.com");
        savedRequest.setEventType("Public School/Non-Profit Event");
        savedRequest.setEventTitle("Endangered Animal Awareness Event");
        savedRequest.setOrganizationName("TCU Environmental Club");
        savedRequest.setEventAddress("2950 W Bowie St, Fort Worth, TX 76109");
        savedRequest.setOnCampus(true);
        savedRequest.setSpecialInstructions("N/A");
        savedRequest.setBenefitsDescription("Allergy possibility with dogs.");
        savedRequest.setSponsorDescription("TCU");
        savedRequest.setDetailedDescription("We will be educating our students on endangered animals.");

        given(this.requestService.save(Mockito.any(Request.class))).willReturn(savedRequest);

        // When and then
        this.mockMvc.perform(post(this.baseUrl + "/requests")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.flag").value(true))
                .andExpect(jsonPath("$.code").value(StatusCode.SUCCESS))
                .andExpect(jsonPath("$.message").value("Add Success"))
                .andExpect(jsonPath("$.data.id").isNotEmpty())
                .andExpect(jsonPath("$.data.firstName").value(savedRequest.getFirstName()))
                .andExpect(jsonPath("$.data.eventTitle").value(savedRequest.getEventTitle()))
                .andExpect(jsonPath("$.data.benefitsDescription").value(savedRequest.getBenefitsDescription()));


    }

    @Test
    void testUpdateRequest() throws Exception {
        // Given
        RequestDto requestDto = new RequestDto("123456789",
                "John",
                "Doe",
                "(123) 456-7890",
                "j.doe@tcu.edu",
                "Private/Residential Event",
                "Graduation Party!",
                "N/A",
                "2701 S Hulen St, Fort Worth, TX 76109",
                false,
                "Please park in the parking lot!",
                "Water and pizza will be provided.",
                "N/A",
                "This event is my son's birthday party.");
        String json = this.objectMapper.writeValueAsString(requestDto);

        Request updatedRequest = new Request();
        updatedRequest.setId("123456789");
        updatedRequest.setFirstName("John");
        updatedRequest.setLastName("Doe");
        updatedRequest.setPhoneNumber("(123) 456-7890");
        updatedRequest.setEmail("j.doe@tcu.edu");
        updatedRequest.setEventType("Private/Residential Event");
        updatedRequest.setEventTitle("Graduation Party!");
        updatedRequest.setOrganizationName("N/A");
        updatedRequest.setEventAddress("2701 S Hulen St, Fort Worth, TX 76109");
        updatedRequest.setOnCampus(false);
        updatedRequest.setSpecialInstructions("Please park in the parking lot!");
        updatedRequest.setBenefitsDescription("Water and pizza will be provided.");
        updatedRequest.setSponsorDescription("N/A");
        updatedRequest.setDetailedDescription("This event is my son's birthday party.");

        given(this.requestService.update(eq("123456789"),
                Mockito.any(Request.class)))
                .willReturn(updatedRequest);

        // When and then
        this.mockMvc.perform(put(this.baseUrl + "/requests/123456789")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.flag").value(true))
                .andExpect(jsonPath("$.code").value(StatusCode.SUCCESS))
                .andExpect(jsonPath("$.message").value("Update Success"))
                .andExpect(jsonPath("$.data.id")
                        .value("123456789"))
                .andExpect(jsonPath("$.data.firstName")
                        .value(updatedRequest.getFirstName()))
                .andExpect(jsonPath("$.data.eventTitle")
                        .value(updatedRequest.getEventTitle()))
                .andExpect(jsonPath("$.data.benefitsDescription")
                        .value(updatedRequest.getBenefitsDescription()));
    }

    @Test
    void testUpdateRequestErrorWithNonExistentId() throws Exception {
        // Given
        RequestDto requestDto = new RequestDto("123456789",
                "John",
                "Doe",
                "(123) 456-7890",
                "j.doe@tcu.edu",
                "Private/Residential Event",
                "Birthday Party!",
                "N/A",
                "2701 S Hulen St, Fort Worth, TX 76109",
                false,
                "Please park in the parking lot!",
                "Water and pizza will be provided.",
                "N/A",
                "This event is my son's birthday party.");
        String json = this.objectMapper.writeValueAsString(requestDto);

        given(this.requestService.update(eq("123456789"),
                Mockito.any(Request.class)))
                .willThrow(new ObjectNotFoundException("request", "123456789"));

        // When and then
        this.mockMvc.perform(put(this.baseUrl + "/requests/123456789")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.flag").value(false))
                .andExpect(jsonPath("$.code").value(StatusCode.NOT_FOUND))
                .andExpect(jsonPath("$.message").value("Could not find request with id 123456789."))
                .andExpect(jsonPath("$.data")
                        .isEmpty());
    }

    @Test
    void testDeleteRequestSuccess() throws Exception {
        // Given
        doNothing().when(this.requestService).delete("123456789");

        // When and then
        this.mockMvc.perform(delete(this.baseUrl + "/requests/123456789")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.flag").value(true))
                .andExpect(jsonPath("$.code").value(StatusCode.SUCCESS))
                .andExpect(jsonPath("$.message").value("Delete Success"))
                .andExpect(jsonPath("$.data")
                        .isEmpty());
    }

    @Test
    void testDeleteRequestErrorWithNonExistentId() throws Exception {
        // Given
        doThrow(new ObjectNotFoundException("request", "123456789"))
                .when(this.requestService)
                .delete("123456789");

        // When and then
        this.mockMvc.perform(delete(this.baseUrl + "/requests/123456789")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.flag").value(false))
                .andExpect(jsonPath("$.code").value(StatusCode.NOT_FOUND))
                .andExpect(jsonPath("$.message").value("Could not find request with id 123456789."))
                .andExpect(jsonPath("$.data")
                        .isEmpty());
    }
}