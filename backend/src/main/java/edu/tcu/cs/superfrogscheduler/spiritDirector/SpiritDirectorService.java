package edu.tcu.cs.superfrogscheduler.spiritDirector;

import edu.tcu.cs.superfrogscheduler.request.Request;
import edu.tcu.cs.superfrogscheduler.request.RequestRepository;
import edu.tcu.cs.superfrogscheduler.request.RequestStatus;
import edu.tcu.cs.superfrogscheduler.superfrog.Superfrog;
import edu.tcu.cs.superfrogscheduler.superfrog.SuperfrogRepository;
import edu.tcu.cs.superfrogscheduler.system.exception.ObjectNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class SpiritDirectorService {

    private SpiritDirectorRepository spiritDirectorRepository; // Assuming you have a repository for data access

    private RequestRepository requestRepository;

    @Autowired
    public SpiritDirectorService(SpiritDirectorRepository spiritDirectorRepository,RequestRepository requestRepository) {
        this.spiritDirectorRepository = spiritDirectorRepository;
        this.requestRepository = requestRepository;
    }

    // Method to approve an appearance request
    public void approveAppearanceRequest(String requestId) throws Exception {
        // Retrieve the request from the database
        Request request = this.requestRepository.findById(requestId)
                .orElseThrow(() -> new ObjectNotFoundException("request", requestId));

        // Check if the request exists and is in "Pending" status
        if (request != null && request.getStatus().equals(RequestStatus.PENDING)) {
            // Set the request status to "Approved"
            request.setStatus(RequestStatus.APPROVED);

            // Update the request in the database
            this.requestRepository.save(request);

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
        Request request = this.requestRepository.findById(requestId)
                .orElseThrow(() -> new ObjectNotFoundException("request", requestId));

        // Check if the request exists and is in "Pending" status
        if (request != null && request.getStatus().equals("Pending")) {
            // Set the request status to "Rejected"
            request.setStatus(RequestStatus.REJECTED);

            // Set the rejection reason
            request.setRejectionReason(reason);

            // Update the request in the database
            this.requestRepository.save(request);

            // Notify relevant parties of the rejection
            notifyRejection(request);
        } else {
            throw new Exception("Request not found or not in a valid state for rejection.");
        }
    }

    // Method to notify parties about approval
    private void notifyApproval(Request request) {
        // Send email or other notification to relevant parties
    }

    // Method to notify parties about rejection
    private void notifyRejection(Request request) {
        // Send email or other notification to relevant parties
    }
}
