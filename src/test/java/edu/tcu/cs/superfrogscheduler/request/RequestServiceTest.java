package edu.tcu.cs.superfrogscheduler.request;

import edu.tcu.cs.superfrogscheduler.system.exception.ObjectNotFoundException;
import edu.tcu.cs.superfrogscheduler.utils.IdWorker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
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
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class RequestServiceTest {

    @Mock
    RequestRepository requestRepository;

    @Mock
    IdWorker idWorker;

    @InjectMocks
    RequestService requestService;

    List<Request> requests;

    @BeforeEach
    void setUp() {
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

        this.requests = new ArrayList<>();
        this.requests.add(r1);
        this.requests.add(r2);
    }

    @Test
    void testFindByIdSuccess() {
        // Given
        Request r = new Request();
        r.setId("123456789");
        r.setFirstName("John");
        r.setLastName("Doe");
        r.setPhoneNumber("(123) 456-7890");
        r.setEmail("j.doe@tcu.edu");
        r.setEventType("Private/Residential Event");
        r.setEventTitle("Birthday Party!");
        r.setOrganizationName("N/A");
        r.setEventAddress("2701 S Hulen St, Fort Worth, TX 76109");
        r.setOnCampus(false);
        r.setSpecialInstructions("Please park in the parking lot!");
        r.setBenefitsDescription("Water and pizza will be provided.");
        r.setSponsorDescription("N/A");
        r.setDetailedDescription("This event is my son's birthday party.");


        given(this.requestRepository.findById("123456789")).willReturn(Optional.of(r)); // Defines the behavior of mock object

        // When
        Request returnedRequest = this.requestService.findById("123456789");

        // Then
        assertThat(returnedRequest.getId()).isEqualTo(r.getId());
        assertThat(returnedRequest.getFirstName()).isEqualTo(r.getFirstName());
        assertThat(returnedRequest.getLastName()).isEqualTo(r.getLastName());
        assertThat(returnedRequest.getPhoneNumber()).isEqualTo(r.getPhoneNumber());
        assertThat(returnedRequest.getEmail()).isEqualTo(r.getEmail());
        assertThat(returnedRequest.getEventType()).isEqualTo(r.getEventType());
        assertThat(returnedRequest.getOrganizationName()).isEqualTo(r.getOrganizationName());
        assertThat(returnedRequest.getEventAddress()).isEqualTo(r.getEventAddress());
        assertThat(returnedRequest.isOnCampus()).isEqualTo(r.isOnCampus());
        assertThat(returnedRequest.getSpecialInstructions()).isEqualTo(r.getSpecialInstructions());
        assertThat(returnedRequest.getBenefitsDescription()).isEqualTo(r.getBenefitsDescription());
        assertThat(returnedRequest.getSponsorDescription()).isEqualTo(r.getSponsorDescription());
        assertThat(returnedRequest.getDetailedDescription()).isEqualTo(r.getDetailedDescription());

        verify(this.requestRepository, times(1)).findById("123456789");
    }

    @Test
    void testFindByIdNotFound() {
        // Given
        given(this.requestRepository.findById(Mockito.any(String.class))).willReturn(Optional.empty());

        // When
        Throwable thrown = catchThrowable(() -> {
            Request returnedArtifact = this.requestService.findById("123456789");
        });

        // Then
        assertThat(thrown).isInstanceOf(ObjectNotFoundException.class)
                .hasMessage("Could not find request with id 123456789.");
        verify(this.requestRepository, times(1)).findById("123456789");

    }

    @Test
    void testFindAllSuccess() {
        // Given
        given(this.requestRepository.findAll()).willReturn(this.requests);

        // When
        List<Request> actualRequests = this.requestService.findAll();

        // Then
        assertThat(actualRequests.size()).isEqualTo(this.requests.size());
        verify(this.requestRepository, times(1)).findAll();
    }

    @Test
    void testSaveSuccess() {
        // Given
        Request newRequest = new Request();
        newRequest.setId("334433443344");
        newRequest.setFirstName("Sam");
        newRequest.setLastName("McDonald");
        newRequest.setPhoneNumber("(555) 666-6565");
        newRequest.setEmail("s.donald@gmail.com");
        newRequest.setEventType("Public School/Non-Profit Event");
        newRequest.setEventTitle("Endangered Animal Awareness Event");
        newRequest.setOrganizationName("TCU Environmental Club");
        newRequest.setEventAddress("2950 W Bowie St, Fort Worth, TX 76109");
        newRequest.setOnCampus(true);
        newRequest.setSpecialInstructions("N/A");
        newRequest.setBenefitsDescription("Allergy possibility with dogs.");
        newRequest.setSponsorDescription("TCU");
        newRequest.setDetailedDescription("We will be educating our students on endangered animals.");


        given(idWorker.nextId()).willReturn(123456L);
        given(this.requestRepository.save(newRequest)).willReturn(newRequest);

        // When
        Request savedRequest = this.requestService.save(newRequest);

        // Then
        assertThat(savedRequest.getId()).isEqualTo("123456");
        assertThat(savedRequest.getFirstName()).isEqualTo(newRequest.getFirstName());
        assertThat(savedRequest.getLastName()).isEqualTo(newRequest.getLastName());
        assertThat(savedRequest.getPhoneNumber()).isEqualTo(newRequest.getPhoneNumber());
        assertThat(savedRequest.getEmail()).isEqualTo(newRequest.getEmail());
        assertThat(savedRequest.getEventType()).isEqualTo(newRequest.getEventType());
        assertThat(savedRequest.getOrganizationName()).isEqualTo(newRequest.getOrganizationName());
        assertThat(savedRequest.getEventAddress()).isEqualTo(newRequest.getEventAddress());
        assertThat(savedRequest.isOnCampus()).isEqualTo(newRequest.isOnCampus());
        assertThat(savedRequest.getSpecialInstructions()).isEqualTo(newRequest.getSpecialInstructions());
        assertThat(savedRequest.getBenefitsDescription()).isEqualTo(newRequest.getBenefitsDescription());
        assertThat(savedRequest.getSponsorDescription()).isEqualTo(newRequest.getSponsorDescription());
        assertThat(savedRequest.getDetailedDescription()).isEqualTo(newRequest.getDetailedDescription());
        verify(requestRepository, times(1)).save(newRequest);
    }

    @Test
    void testUpdateSuccess() {
        // Given
        Request oldRequest = new Request();
        oldRequest.setId("123456789");
        oldRequest.setFirstName("John");
        oldRequest.setLastName("Doe");
        oldRequest.setPhoneNumber("(123) 456-7890");
        oldRequest.setEmail("j.doe@tcu.edu");
        oldRequest.setEventType("Private/Residential Event");
        oldRequest.setEventTitle("Birthday Party!");
        oldRequest.setOrganizationName("N/A");
        oldRequest.setEventAddress("2701 S Hulen St, Fort Worth, TX 76109");
        oldRequest.setOnCampus(false);
        oldRequest.setSpecialInstructions("Please park in the parking lot!");
        oldRequest.setBenefitsDescription("Water and pizza will be provided.");
        oldRequest.setSponsorDescription("N/A");
        oldRequest.setDetailedDescription("This event is my son's birthday party.");


        Request update = new Request();
        update.setId("123456789");
        update.setFirstName("John");
        update.setLastName("Doe");
        update.setPhoneNumber("(123) 456-7890");
        update.setEmail("j.doe@tcu.edu");
        update.setEventType("Private/Residential Event");
        update.setEventTitle("Graduation Party!");
        update.setOrganizationName("N/A");
        update.setEventAddress("2701 S Hulen St, Fort Worth, TX 76109");
        update.setOnCampus(false);
        update.setSpecialInstructions("Please park in the parking lot!");
        update.setBenefitsDescription("Water and pizza will be provided.");
        update.setSponsorDescription("N/A");
        update.setDetailedDescription("This event is my son's birthday party.");


        given(this.requestRepository.findById("123456789")).willReturn(Optional.of(oldRequest));
        given(this.requestRepository.save(oldRequest)).willReturn(oldRequest);

        // When
        Request updatedArtifact = this.requestService.update("123456789", update);

        // Then
        assertThat(updatedArtifact.getId()).isEqualTo("123456789");
        assertThat(updatedArtifact.getDetailedDescription()).isEqualTo(update.getDetailedDescription());
        verify(this.requestRepository, times(1)).findById("123456789");
        verify(this.requestRepository, times(1)).save(oldRequest);

    }

    @Test
    void testUpdateNotFound() {
        // Given
        Request update = new Request();
        update.setId("123456789");
        update.setFirstName("John");
        update.setLastName("Doe");
        update.setPhoneNumber("(123) 456-7890");
        update.setEmail("j.doe@tcu.edu");
        update.setEventType("Private/Residential Event");
        update.setEventTitle("Graduation Party!");
        update.setOrganizationName("N/A");
        update.setEventAddress("2701 S Hulen St, Fort Worth, TX 76109");
        update.setOnCampus(false);
        update.setSpecialInstructions("Please park in the parking lot!");
        update.setBenefitsDescription("Water and pizza will be provided.");
        update.setSponsorDescription("N/A");
        update.setDetailedDescription("This event is my son's birthday party.");

        given(this.requestRepository.findById("123456789")).willReturn(Optional.empty());

        // When
        assertThrows(ObjectNotFoundException.class, () -> {
            this.requestService.update("123456789", update);
        });

        // Then
        verify(this.requestRepository, times(1)).findById("123456789");

    }

    @Test
    void testDeleteSuccess() {
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

        given(this.requestRepository.findById("123456789")).willReturn(Optional.of(request));
        doNothing().when(this.requestRepository).deleteById("123456789");

        // When
        this.requestService.delete("123456789");

        // Then
        verify(requestRepository, times(1)).deleteById("123456789");
    }

    @Test
    void testDeleteNotFound() {
        // Given
        given(this.requestRepository.findById("123456789")).willReturn(Optional.empty());

        // When
        assertThrows(ObjectNotFoundException.class, () -> {
            this.requestService.delete("123456789");
        });

        // Then
        verify(this.requestRepository, times(1)).findById("123456789");
    }
}