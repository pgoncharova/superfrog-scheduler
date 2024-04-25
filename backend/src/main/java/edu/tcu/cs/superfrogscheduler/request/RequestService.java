package edu.tcu.cs.superfrogscheduler.request;

import edu.tcu.cs.superfrogscheduler.system.exception.ObjectNotFoundException;
import edu.tcu.cs.superfrogscheduler.utils.IdWorker;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class RequestService {
    // VARIABLES:
    private final RequestRepository requestRepository;

    private final IdWorker idWorker;

    // CONSTRUCTOR:
    public RequestService(RequestRepository requestRepository, IdWorker idWorker) {
        this.requestRepository = requestRepository;
        this.idWorker = idWorker;
    }

    // METHODS:
    public Request findById(String id) {
        return this.requestRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("request", id));
    }

    public List<Request> findAll() {
        return this.requestRepository.findAll();
    }

    public Request save(Request newRequest) {
        newRequest.setId(idWorker.nextId() + "");
        return this.requestRepository.save(newRequest);
    }

    public Request update(String id, Request update) {
        return this.requestRepository.findById(id)
                .map(oldRequest -> {
                    oldRequest.setFirstName(update.getFirstName());
                    oldRequest.setLastName(update.getLastName());
                    oldRequest.setPhoneNumber(update.getPhoneNumber());
                    oldRequest.setEmail(update.getEmail());
                    oldRequest.setEventType(update.getEventType());
                    oldRequest.setEventTitle(update.getEventTitle());
                    oldRequest.setOrganizationName(update.getOrganizationName());
                    oldRequest.setEventAddress(update.getEventAddress());
                    oldRequest.setOnCampus(update.isOnCampus());
                    oldRequest.setSpecialInstructions(update.getSpecialInstructions());
                    oldRequest.setBenefitsDescription(update.getBenefitsDescription());
                    oldRequest.setSponsorDescription(update.getSponsorDescription());
                    oldRequest.setDetailedDescription(update.getDetailedDescription());
                    oldRequest.setStatus(update.getStatus());
                    oldRequest.setRejectionReason(update.getRejectionReason());
                    oldRequest.setOwner(update.getOwner());
                    oldRequest.setSuperfrog(update.getSuperfrog());
                    return this.requestRepository.save(oldRequest);
                })
                .orElseThrow(() -> new ObjectNotFoundException("request", id));
    }

    public void delete(String id) {
        this.requestRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("request", id));
        this.requestRepository.deleteById(id);
    }

    public void approve(String id) {
        Request request = findById(id);
        request.setStatus(RequestStatus.APPROVED);
        save(request);
    }

    public void reject(String id, String rejectionReason) {
        Request request = findById(id);
        request.setStatus(RequestStatus.REJECTED);
        request.setRejectionReason(rejectionReason);
        save(request);
    }

    public List<Request> search(Map<String, String> searchCriteria) {
        List<Request> requests = new ArrayList<>();

        String firstName = searchCriteria.get("firstName");
        String lastName = searchCriteria.get("lastName");
        String eventType = searchCriteria.get("eventType");
        String eventTitle = searchCriteria.get("eventTitle");
        String organizationName = searchCriteria.get("organizationName");
        String status = searchCriteria.get("status");

        // Search by firstName
        if (firstName != null && !firstName.isEmpty()) {
            List<Request> byFirstName = requestRepository.findByFirstName(firstName);
            requests.addAll(byFirstName);
        }

        // Search by lastName
        if (lastName != null && !lastName.isEmpty()) {
            List<Request> byLastName = requestRepository.findByLastName(lastName);
            requests.addAll(byLastName);
        }

        // Search by eventType
        if (eventType != null && !eventType.isEmpty()) {
            List<Request> byEventType = requestRepository.findByEventType(eventType);
            requests.addAll(byEventType);
        }

        // Search by eventTitle
        if (eventTitle != null && !eventTitle.isEmpty()) {
            List<Request> byEventTitle = requestRepository.findByEventTitle(eventTitle);
            requests.addAll(byEventTitle);
        }

        // Search by organizationName
        if (organizationName != null && !organizationName.isEmpty()) {
            List<Request> byOrganizationName = requestRepository.findByOrganizationName(organizationName);
            requests.addAll(byOrganizationName);
        }

        // Search by status
        if (status != null && !status.isEmpty()) {
            RequestStatus requestStatus = RequestStatus.valueOf(status.toUpperCase());
            List<Request> byStatus = requestRepository.findByStatus(requestStatus);
            requests.addAll(byStatus);
        }

        return requests;
    }


}
