package edu.tcu.cs.superfrogscheduler.request;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/event-requests")
public class EventRequestController {

    @Autowired
    private EventRequestService eventRequestService;

    // Endpoint to approve an event request
    @PostMapping("/{requestId}/approve")
    public ResponseEntity<?> approveEventRequest(@PathVariable String requestId) {
        try {
            eventRequestService.approveEventRequest(requestId);
            return ResponseEntity.ok("Event request approved successfully.");
        } catch (Exception e) {
            // Handle exceptions properly, perhaps returning different HTTP statuses based on exception types
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // Endpoint to reject an event request
    @PostMapping("/{requestId}/reject")
    public ResponseEntity<?> rejectEventRequest(@PathVariable String requestId, @RequestBody String reason) {
        try {
            eventRequestService.rejectEventRequest(requestId, reason);
            return ResponseEntity.ok("Event request rejected successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // Endpoint to create a new event request
    @PostMapping("/create")
    public ResponseEntity<?> createEventRequest(@RequestBody EventRequest newRequest, @RequestParam String superFrogStudentId) {
        try {
            EventRequest createdRequest = eventRequestService.createEventRequest(newRequest, superFrogStudentId);
            return new ResponseEntity<>(createdRequest, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // Endpoint to search for event requests based on criteria
    @GetMapping("/search")
    public List<EventRequest> searchEventRequests(@RequestParam Map<String, Object> searchCriteria) {
        return eventRequestService.searchEventRequests(searchCriteria);
    }

    // Endpoint to view an event request
    @GetMapping("/{requestId}")
    public ResponseEntity<?> viewEventRequest(@PathVariable String requestId) {
        try {
            EventRequest eventRequest = eventRequestService.viewEventRequest(requestId);
            return ResponseEntity.ok(eventRequest);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    // Add more endpoints as needed for other functionalities

}
