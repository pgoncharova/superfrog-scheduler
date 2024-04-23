package edu.tcu.cs.superfrogscheduler.request;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class EventRequestService {

    @Autowired
    private EventRequestRepository eventRequestRepository; // Your event request repository

    // Method to approve an event request
    public void approveEventRequest(String requestId) throws Exception {
        Optional<EventRequest> eventRequestOptional = eventRequestRepository.findById(requestId);

        if (eventRequestOptional.isPresent()) {
            EventRequest eventRequest = eventRequestOptional.get();

            // Check if the request is in "Pending" status
            if (eventRequest.getStatus().equals(EventRequest.EventRequestStatus.PENDING)) {
                // Set the request status to "Approved"
                eventRequest.setStatus(EventRequest.EventRequestStatus.APPROVED);

                // Save the updated event request
                eventRequestRepository.save(eventRequest);

                // Additional logic to handle conflicts with other pending requests

                // Notify relevant parties of the approval
                notifyApproval(eventRequest);
            } else {
                throw new Exception("Request is not in a valid state for approval.");
            }
        } else {
            throw new Exception("Request not found.");
        }
    }

    // Method to reject an event request
    public void rejectEventRequest(String requestId, String reason) throws Exception {
        Optional<EventRequest> eventRequestOptional = eventRequestRepository.findById(requestId);

        if (eventRequestOptional.isPresent()) {
            EventRequest eventRequest = eventRequestOptional.get();

            // Check if the request is in "Pending" status
            if (eventRequest.getStatus().equals(EventRequest.EventRequestStatus.PENDING)) {
                // Set the request status to "Rejected"
                eventRequest.setStatus(EventRequest.EventRequestStatus.REJECTED);

                // Set the rejection reason
                eventRequest.setRejectionReason(reason);

                // Save the updated event request
                eventRequestRepository.save(eventRequest);

                // Notify relevant parties of the rejection
                notifyRejection(eventRequest);
            } else {
                throw new Exception("Request is not in a valid state for rejection.");
            }
        } else {
            throw new Exception("Request not found.");
        }
    }

    // Method to create a new event request for a SuperFrog appearance
    public EventRequest createEventRequest(EventRequest newRequest, String superFrogStudentId) throws Exception {
        // Validate the input (dates, times, details)
        validateEventRequest(newRequest);

        // Check if the SuperFrog student is available for the event
        boolean isAvailable = checkSuperFrogAvailability(superFrogStudentId, newRequest.getEventDate());

        if (isAvailable) {
            // Assign the SuperFrog student to the request
            newRequest.setAssignedSuperFrogStudentId(superFrogStudentId);

            // Set the initial status of the event request
            newRequest.setStatus(EventRequest.EventRequestStatus.PENDING);

            // Save the new event request
            EventRequest savedRequest = eventRequestRepository.save(newRequest);

            // Notify the Spirit Director that the request has been created
            notifyRequestCreation(savedRequest);

            return savedRequest;
        } else {
            throw new Exception("SuperFrog student is not available for the requested event date.");
        }
    }

    // Method to search for event requests based on criteria
    public List<EventRequest> searchEventRequests(Map<String, Object> searchCriteria) {
        // Convert search criteria into a Specification or use repository method to search
        return eventRequestRepository.findAllWithCriteria(searchCriteria);
    }

    // Method to view details of an event request
    public EventRequest viewEventRequest(String requestId) throws Exception {
        return eventRequestRepository.findById(requestId)
                .orElseThrow(() -> new Exception("Event request not found."));
    }


    // Example repository method
    public interface EventRequestRepository extends JpaRepository<EventRequest, String> {
        List<EventRequest> findAllWithCriteria(Map<String, Object> criteria);
    }


    private void validateEventRequest(EventRequest request) {
        // Logic to validate the new event request details
    }

    private boolean checkSuperFrogAvailability(String studentId, LocalDate eventDate) {
        // Logic to check the availability of the SuperFrog student
        return true; // Placeholder for actual availability check
    }

    private void notifyRequestCreation(EventRequest request) {
        // Logic to notify the creation of the event request
    }


    // Placeholder methods for notification logic
    private void notifyApproval(EventRequest eventRequest) {
        // Notification logic for approval
    }

    private void notifyRejection(EventRequest eventRequest) {
        // Notification logic for rejection
    }
}
