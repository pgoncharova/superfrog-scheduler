package edu.tcu.cs.superfrogscheduler.customer;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import edu.tcu.cs.superfrogscheduler.request.EventRequest;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CustomerControllerTests {

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void testUpdateRequest() throws Exception {
        EventRequest requestDetails = new EventRequest(); // Assume proper initialization
        String requestJson = convertToJson(requestDetails);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/customers/1/requests/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("Updated"));
    }

    @Test
    public void testCancelRequest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.patch("/api/customers/1/requests/1/cancel"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("Cancelled"));
    }


    private String convertToJson(Object obj) throws JsonProcessingException {
        return objectMapper.writeValueAsString(obj);
    }

    @Test
    public void testCreateEventRequest() throws Exception {
        EventRequest mockRequest = new EventRequest(); // Initialize with test data
        String requestJson = objectMapper.writeValueAsString(mockRequest);

        mockMvc.perform(post("/api/customers/1/events", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.eventType", is(mockRequest.getEventType())))
                .andExpect(jsonPath("$.status", is("Pending")));
    }

}
