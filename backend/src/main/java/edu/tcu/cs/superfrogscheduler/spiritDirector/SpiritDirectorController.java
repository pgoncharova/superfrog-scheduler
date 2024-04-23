package edu.tcu.cs.superfrogscheduler.spiritDirector;

import edu.tcu.cs.superfrogscheduler.request.Request;
import edu.tcu.cs.superfrogscheduler.request.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/spirit-director")
public class SpiritDirectorController {

    private final RequestService requestService;

    @Autowired
    public SpiritDirectorController(RequestService requestService) {
        this.requestService = requestService;
    }

    // Endpoint for the Spirit Director to approve an appearance request
    @PostMapping("/requests/{requestId}/approve")
    public ResponseEntity<?> approveAppearanceRequest(@PathVariable String requestId) {
        try {
            requestService.approve(requestId);
            return ResponseEntity.ok("Appearance request approved successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error approving request: " + e.getMessage());
        }
    }

    // Endpoint for the Spirit Director to reject an appearance request
    @PostMapping("/requests/{requestId}/reject")
    public ResponseEntity<?> rejectAppearanceRequest(@PathVariable String requestId, @RequestBody String reason) {
        try {
            requestService.reject(requestId, reason);
            return ResponseEntity.ok("Appearance request rejected successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error rejecting request: " + e.getMessage());
        }
    }

    // Endpoint to create a new appearance request for a TCU event
    @PostMapping("/requests")
    public ResponseEntity<?> createAppearanceRequest(@RequestBody Request newRequest) {
        try {
            Request createdRequest = requestService.save(newRequest);
            return new ResponseEntity<>(createdRequest, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error creating request: " + e.getMessage());
        }
    }

    // Endpoint to find appearance requests based on criteria
    @GetMapping("/requests")
    public ResponseEntity<?> findAppearanceRequests(@RequestParam Map<String, String> searchCriteria) {
        try {
            List<Request> requests = requestService.search(searchCriteria);
            return ResponseEntity.ok(requests);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error finding requests: " + e.getMessage());
        }
    }

    // Endpoint to view details of a specific appearance request
    @GetMapping("/requests/{requestId}")
    public ResponseEntity<?> viewAppearanceRequest(@PathVariable String requestId) {
        try {
            Request request = requestService.findById(requestId);
            return ResponseEntity.ok(request);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Request not found: " + e.getMessage());
        }
    }

    // Additional endpoints as needed

    // Note: Exception handling can be further improved with @ControllerAdvice
}
