package edu.tcu.cs.superfrogscheduler.superfrog;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.tcu.cs.superfrogscheduler.request.Request;
import edu.tcu.cs.superfrogscheduler.request.RequestService;
import edu.tcu.cs.superfrogscheduler.system.StatusCode;
import edu.tcu.cs.superfrogscheduler.system.exception.ObjectNotFoundException;
import edu.tcu.cs.superfrogscheduler.utils.dto.RequestDto;
import edu.tcu.cs.superfrogscheduler.utils.dto.SuperfrogDto;
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
class SuperfrogControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    SuperfrogService superfrogService;

    @Autowired
    ObjectMapper objectMapper;

    List<Superfrog> superfrogs;

    //@Value("${api.endpoint.base-url}")
    String baseUrl = "/api";

    @BeforeEach
    void setUp() {
        this.superfrogs = new ArrayList<>();

        Superfrog s1 = new Superfrog();
        s1.setFirstName("Super");
        s1.setLastName("Frog");
        s1.setPhoneNumber("(333) 444-5555");
        s1.setPhysicalAddress("Foster Hall, Fort Worth, TX 76129");
        s1.setEmail("s.frog@tcu.edu");
        this.superfrogs.add(s1);

        Superfrog s2 = new Superfrog();
        s2.setFirstName("Better");
        s2.setLastName("Frog");
        s2.setPhoneNumber("(555) 222-1010");
        s2.setPhysicalAddress("Jarvis Hall, Fort Worth, TX 76129");
        s2.setEmail("jfrogs.super@tcu.edu");
        this.superfrogs.add(s2);

    }

    @AfterEach
    void tearDown() {

    }

    @Test
    void testFindByIdSuccess() throws Exception {
        // Given
        given(this.superfrogService.findById("s.frog@tcu.edu"))
                .willReturn(this.superfrogs.get(0));
        // When and then
        this.mockMvc.perform(get(this.baseUrl + "/superfrogs/s.frog@tcu.edu")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.flag")
                        .value(true))
                .andExpect(jsonPath("$.code")
                        .value(StatusCode.SUCCESS))
                .andExpect(jsonPath("$.message")
                        .value("Find One Success"))
                .andExpect(jsonPath("$.data.email")
                        .value("s.frog@tcu.edu"));
    }

    @Test
    void testFindByIdNotFound() throws Exception {
        // Given
        given(this.superfrogService.findById("s.frog@tcu.edu"))
                .willThrow(new ObjectNotFoundException("superfrog", "s.frog@tcu.edu"));
        // When and then
        this.mockMvc.perform(get(this.baseUrl + "/superfrogs/s.frog@tcu.edu")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.flag")
                        .value(false))
                .andExpect(jsonPath("$.code")
                        .value(StatusCode.NOT_FOUND))
                .andExpect(jsonPath("$.message")
                        .value("Could not find superfrog with id s.frog@tcu.edu."))
                .andExpect(jsonPath("$.data")
                        .isEmpty());
    }

    @Test
    void testFindAllSuccess() throws Exception {
        // Given
        given(this.superfrogService.findAll()).willReturn(this.superfrogs);
        // When and then
        this.mockMvc.perform(get(this.baseUrl + "/superfrogs")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.flag")
                        .value(true))
                .andExpect(jsonPath("$.code")
                        .value(StatusCode.SUCCESS))
                .andExpect(jsonPath("$.message")
                        .value("Find All Success"))
                .andExpect(jsonPath("$.data", Matchers.hasSize(this.superfrogs.size())))
                .andExpect(jsonPath("$.data[0].email")
                        .value("s.frog@tcu.edu"))
                .andExpect(jsonPath("$.data[0].phoneNumber")
                        .value("(333) 444-5555"))
                .andExpect(jsonPath("$.data[1].email")
                        .value("jfrogs.super@tcu.edu"))
                .andExpect(jsonPath("$.data[1].phoneNumber")
                        .value("(555) 222-1010"));
    }

    @Test
    void testAdd() throws Exception {
        // Given
        SuperfrogDto superfrogDto = new SuperfrogDto("New",
                "Frog",
                "(777) 888-8989",
                "Beasley Hall, Fort Worth, TX 76129",
                "newbiefrogrules@tcu.edu");
        String json = this.objectMapper.writeValueAsString(superfrogDto);

        Superfrog savedSuperfrog = new Superfrog();
        savedSuperfrog.setFirstName("New");
        savedSuperfrog.setLastName("Frog");
        savedSuperfrog.setPhoneNumber("(777) 888-8989");
        savedSuperfrog.setPhysicalAddress("Beasley Hall, Fort Worth, TX 76129");
        savedSuperfrog.setEmail("newbiefrogrules@tcu.edu");

        given(this.superfrogService.save(Mockito.any(Superfrog.class))).willReturn(savedSuperfrog);

        // When and then
        this.mockMvc.perform(post(this.baseUrl + "/superfrogs")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.flag").value(true))
                .andExpect(jsonPath("$.code").value(StatusCode.SUCCESS))
                .andExpect(jsonPath("$.message").value("Add Success"))
                .andExpect(jsonPath("$.data.email").value(savedSuperfrog.getEmail()))
                .andExpect(jsonPath("$.data.firstName").value(savedSuperfrog.getFirstName()))
                .andExpect(jsonPath("$.data.phoneNumber").value(savedSuperfrog.getPhoneNumber()))
                .andExpect(jsonPath("$.data.physicalAddress").value(savedSuperfrog.getPhysicalAddress()));


    }

    @Test
    void testUpdate() throws Exception {
        // Given
        SuperfrogDto superfrogDto = new SuperfrogDto("Super",
                "Frog",
                "(333) 444-5555",
                "Foster Hall, Fort Worth, TX 76129",
                "s.frog@tcu.edu");
        String json = this.objectMapper.writeValueAsString(superfrogDto);

        Superfrog updatedSuperfrog = new Superfrog();
        updatedSuperfrog.setFirstName("Super");
        updatedSuperfrog.setLastName("Frog");
        updatedSuperfrog.setPhoneNumber("(333) 444-5555");
        updatedSuperfrog.setPhysicalAddress("Foster Hall, Fort Worth, TX 76129");
        updatedSuperfrog.setEmail("s.frog@tcu.edu");

        given(this.superfrogService.update(eq("s.frog@tcu.edu"),
                Mockito.any(Superfrog.class)))
                .willReturn(updatedSuperfrog);

        // When and then
        this.mockMvc.perform(put(this.baseUrl + "/superfrogs/s.frog@tcu.edu")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.flag").value(true))
                .andExpect(jsonPath("$.code").value(StatusCode.SUCCESS))
                .andExpect(jsonPath("$.message").value("Update Success"))
                .andExpect(jsonPath("$.data.email")
                        .value("s.frog@tcu.edu"))
                .andExpect(jsonPath("$.data.firstName")
                        .value(updatedSuperfrog.getFirstName()))
                .andExpect(jsonPath("$.data.phoneNumber")
                        .value(updatedSuperfrog.getPhoneNumber()))
                .andExpect(jsonPath("$.data.physicalAddress")
                        .value(updatedSuperfrog.getPhysicalAddress()));
    }

    @Test
    void testUpdateErrorWithNonExistentId() throws Exception {
        // Given
        SuperfrogDto superfrogDto = new SuperfrogDto("Super",
                "Frog",
                "(333) 444-5555",
                "Foster Hall, Fort Worth, TX 76129",
                "s.frog@tcu.edu");
        String json = this.objectMapper.writeValueAsString(superfrogDto);

        given(this.superfrogService.update(eq("s.frog@tcu.edu"),
                Mockito.any(Superfrog.class)))
                .willThrow(new ObjectNotFoundException("superfrog", "s.frog@tcu.edu"));

        // When and then
        this.mockMvc.perform(put(this.baseUrl + "/superfrogs/s.frog@tcu.edu")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.flag").value(false))
                .andExpect(jsonPath("$.code").value(StatusCode.NOT_FOUND))
                .andExpect(jsonPath("$.message").value("Could not find superfrog with id s.frog@tcu.edu."))
                .andExpect(jsonPath("$.data")
                        .isEmpty());
    }

    @Test
    void testDeleteSuccess() throws Exception {
        // Given
        doNothing().when(this.superfrogService).delete("s.frog@tcu.edu");

        // When and then
        this.mockMvc.perform(delete(this.baseUrl + "/superfrogs/s.frog@tcu.edu")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.flag").value(true))
                .andExpect(jsonPath("$.code").value(StatusCode.SUCCESS))
                .andExpect(jsonPath("$.message").value("Delete Success"))
                .andExpect(jsonPath("$.data")
                        .isEmpty());
    }

    @Test
    void testDeleteErrorWithNonExistentId() throws Exception {
        // Given
        doThrow(new ObjectNotFoundException("superfrog", "s.frog@tcu.edu"))
                .when(this.superfrogService)
                .delete("s.frog@tcu.edu");

        // When and then
        this.mockMvc.perform(delete(this.baseUrl + "/superfrogs/s.frog@tcu.edu")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.flag").value(false))
                .andExpect(jsonPath("$.code").value(StatusCode.NOT_FOUND))
                .andExpect(jsonPath("$.message").value("Could not find superfrog with id s.frog@tcu.edu."))
                .andExpect(jsonPath("$.data")
                        .isEmpty());
    }
}