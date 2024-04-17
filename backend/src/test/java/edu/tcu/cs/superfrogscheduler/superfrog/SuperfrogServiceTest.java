package edu.tcu.cs.superfrogscheduler.superfrog;

import edu.tcu.cs.superfrogscheduler.system.exception.ObjectNotFoundException;
import edu.tcu.cs.superfrogscheduler.utils.IdWorker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
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
class SuperfrogServiceTest {

    @Mock
    SuperfrogRepository superfrogRepository;

    @InjectMocks
    SuperfrogService superfrogService;

    List<Superfrog> superfrogs;

    @BeforeEach
    void setUp() {
        Superfrog s1 = new Superfrog();
        s1.setEmail("thebestfrog@tcu.edu");
        s1.setFirstName("Super");
        s1.setLastName("Frog");
        s1.setPhoneNumber("(888) 888-8888");
        s1.setPhysicalAddress("2900 S University Dr, Fort Worth, TX 76129");

        Superfrog s2 = new Superfrog();
        s2.setEmail("lizard@tcu.edu");
        s2.setFirstName("Horned");
        s2.setLastName("Lizard");
        s2.setPhoneNumber("(222) 222-2222");
        s2.setPhysicalAddress("3100 Main Drive, Fort Worth, TX 76129");

        this.superfrogs = new ArrayList<>();
        this.superfrogs.add(s1);
        this.superfrogs.add(s2);
    }

    @Test
    void testFindByIdSuccess() {
        // Given
        Superfrog s = new Superfrog();
        s.setFirstName("Super");
        s.setLastName("Frog");
        s.setPhoneNumber("(888) 888-8888");
        s.setEmail("thebestfrog@tcu.edu");
        s.setPhysicalAddress("2900 S University Dr, Fort Worth, TX 76129");


        given(this.superfrogRepository.findById("thebestfrog@tcu.edu")).willReturn(Optional.of(s)); // Defines the behavior of mock object

        // When
        Superfrog returnedSuperfrog = this.superfrogService.findById("thebestfrog@tcu.edu");

        // Then
        assertThat(returnedSuperfrog.getFirstName()).isEqualTo(s.getFirstName());
        assertThat(returnedSuperfrog.getLastName()).isEqualTo(s.getLastName());
        assertThat(returnedSuperfrog.getPhoneNumber()).isEqualTo(s.getPhoneNumber());
        assertThat(returnedSuperfrog.getEmail()).isEqualTo(s.getEmail());
        assertThat(returnedSuperfrog.getPhysicalAddress()).isEqualTo(s.getPhysicalAddress());

        verify(this.superfrogRepository, times(1)).findById("thebestfrog@tcu.edu");
    }

    @Test
    void testFindByIdNotFound() {
        // Given
        given(this.superfrogRepository.findById(Mockito.any(String.class))).willReturn(Optional.empty());

        // When
        Throwable thrown = catchThrowable(() -> {
            Superfrog returnedSuperfrog = this.superfrogService.findById("thebestfrog@tcu.edu");
        });

        // Then
        assertThat(thrown).isInstanceOf(ObjectNotFoundException.class)
                .hasMessage("Could not find superfrog with id thebestfrog@tcu.edu.");
        verify(this.superfrogRepository, times(1)).findById("thebestfrog@tcu.edu");

    }

    @Test
    void testFindAllSuccess() {
        // Given
        given(this.superfrogRepository.findAll()).willReturn(this.superfrogs);

        // When
        List<Superfrog> actualSuperfrogs = this.superfrogService.findAll();

        // Then
        assertThat(actualSuperfrogs.size()).isEqualTo(this.superfrogs.size());
        verify(this.superfrogRepository, times(1)).findAll();
    }

    @Test
    void testSaveSuccess() {
        // Given
        Superfrog newSuperfrog = new Superfrog();
        newSuperfrog.setFirstName("Kate");
        newSuperfrog.setLastName("Jarvis");
        newSuperfrog.setPhoneNumber("(876) 876-8766");
        newSuperfrog.setEmail("kate.jar@gmail.com");
        newSuperfrog.setPhysicalAddress("2100 Log Cabin Village Ln, Fort Worth, TX 76109");

        given(this.superfrogRepository.save(newSuperfrog)).willReturn(newSuperfrog);

        // When
        Superfrog savedSuperfrog = this.superfrogService.save(newSuperfrog);

        // Then
        assertThat(savedSuperfrog.getFirstName()).isEqualTo(newSuperfrog.getFirstName());
        assertThat(savedSuperfrog.getLastName()).isEqualTo(newSuperfrog.getLastName());
        assertThat(savedSuperfrog.getPhoneNumber()).isEqualTo(newSuperfrog.getPhoneNumber());
        assertThat(savedSuperfrog.getEmail()).isEqualTo(newSuperfrog.getEmail());
        assertThat(savedSuperfrog.getPhysicalAddress()).isEqualTo(newSuperfrog.getPhysicalAddress());
        verify(superfrogRepository, times(1)).save(newSuperfrog);
    }

    @Test
    void testUpdateSuccess() {
        // Given
        Superfrog oldSuperfrog = new Superfrog();
        oldSuperfrog.setFirstName("Super");
        oldSuperfrog.setLastName("Frog");
        oldSuperfrog.setPhoneNumber("(888) 888-8888");
        oldSuperfrog.setEmail("thebestfrog@tcu.edu");
        oldSuperfrog.setPhysicalAddress("2900 S University Dr, Fort Worth, TX 76129");


        Superfrog update = new Superfrog();
        update.setFirstName("Super");
        update.setLastName("Frogg");
        update.setPhoneNumber("(888) 888-8888");
        update.setEmail("thebestfrog@tcu.edu");
        update.setPhysicalAddress("2900 S University Dr, Fort Worth, TX 76129");


        given(this.superfrogRepository.findById("thebestfrog@tcu.edu")).willReturn(Optional.of(oldSuperfrog));
        given(this.superfrogRepository.save(oldSuperfrog)).willReturn(oldSuperfrog);

        // When
        Superfrog updatedSuperfrog = this.superfrogService.update("thebestfrog@tcu.edu", update);

        // Then
        assertThat(updatedSuperfrog.getEmail()).isEqualTo("thebestfrog@tcu.edu");
        assertThat(updatedSuperfrog.getLastName()).isEqualTo(update.getLastName());
        verify(this.superfrogRepository, times(1)).findById("thebestfrog@tcu.edu");
        verify(this.superfrogRepository, times(1)).save(oldSuperfrog);

    }

    @Test
    void testUpdateNotFound() {
        // Given
        Superfrog update = new Superfrog();
        update.setFirstName("Super");
        update.setLastName("Frogg");
        update.setPhoneNumber("(888) 888-8888");
        update.setEmail("thebestfrog@tcu.edu");
        update.setPhysicalAddress("2900 S University Dr, Fort Worth, TX 76129");

        given(this.superfrogRepository.findById("thebestfrog@tcu.edu")).willReturn(Optional.empty());

        // When
        assertThrows(ObjectNotFoundException.class, () -> {
            this.superfrogService.update("thebestfrog@tcu.edu", update);
        });

        // Then
        verify(this.superfrogRepository, times(1)).findById("thebestfrog@tcu.edu");

    }

    @Test
    void testDeleteSuccess() {
        // Given
        Superfrog request = new Superfrog();
        request.setFirstName("Super");
        request.setLastName("Frog");
        request.setPhoneNumber("(888) 888-8888");
        request.setEmail("thebestfrog@tcu.edu");
        request.setPhysicalAddress("2900 S University Dr, Fort Worth, TX 76129");

        given(this.superfrogRepository.findById("thebestfrog@tcu.edu")).willReturn(Optional.of(request));
        doNothing().when(this.superfrogRepository).deleteById("thebestfrog@tcu.edu");

        // When
        this.superfrogService.delete("thebestfrog@tcu.edu");

        // Then
        verify(superfrogRepository, times(1)).deleteById("thebestfrog@tcu.edu");
    }

    @Test
    void testDeleteNotFound() {
        // Given
        given(this.superfrogRepository.findById("thebestfrog@tcu.edu")).willReturn(Optional.empty());

        // When
        assertThrows(ObjectNotFoundException.class, () -> {
            this.superfrogService.delete("thebestfrog@tcu.edu");
        });

        // Then
        verify(this.superfrogRepository, times(1)).findById("thebestfrog@tcu.edu");
    }
}