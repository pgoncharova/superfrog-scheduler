package edu.tcu.cs.superfrogscheduler.spiritDirector;

import edu.tcu.cs.superfrogscheduler.request.EventRequest;
import edu.tcu.cs.superfrogscheduler.superfrog.Superfrog;
import edu.tcu.cs.superfrogscheduler.superfrog.SuperfrogRepository;
import edu.tcu.cs.superfrogscheduler.system.exception.ObjectNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpiritDirectorService {

    @Autowired
    private SpiritDirectorRepository spiritDirectorRepository; // Assuming you have a repository for data access

    // Method to approve an appearance request
    public void approveAppearanceRequest(String requestId) throws Exception {
        // Retrieve the request from the database
        EventRequest request = spiritDirectorRepository.findById(requestId);

        // Check if the request exists and is in "Pending" status
        if (request != null && request.getStatus().equals("Pending")) {
            // Set the request status to "Approved"
            request.setStatus("Approved");

            // Update the request in the database
            spiritDirectorRepository.save(request);

            // Logic to handle conflicts with other pending requests, if necessary

            // Notify relevant parties of the approval
            notifyApproval(request);
        } else {
            throw new Exception("Request not found or not in a valid state for approval.");
        }
    }

    // Method to reject an appearance request
    public void rejectAppearanceRequest(String requestId, String reason) throws Exception {
        // Retrieve the request from the database
        EventRequest request = spiritDirectorRepository.findById(requestId);

        // Check if the request exists and is in "Pending" status
        if (request != null && request.getStatus().equals("Pending")) {
            // Set the request status to "Rejected"
            request.setStatus("Rejected");

            // Set the rejection reason
            request.setRejectionReason(reason);

            // Update the request in the database
            spiritDirectorRepository.save(request);

            // Notify relevant parties of the rejection
            notifyRejection(request);
        } else {
            throw new Exception("Request not found or not in a valid state for rejection.");
        }
    }

    // Method to notify parties about approval
    private void notifyApproval(EventRequest request) {
        // Send email or other notification to relevant parties
    }

    // Method to notify parties about rejection
    private void notifyRejection(EventRequest request) {
        // Send email or other notification to relevant parties
    }
}
