package edu.tcu.cs.superfrogscheduler.request;

import edu.tcu.cs.superfrogscheduler.utils.IdWorker;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.List;

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
                .orElseThrow(() -> new RequestNotFoundException(id));
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
                    return this.requestRepository.save(oldRequest);
                })
                .orElseThrow(() -> new RequestNotFoundException(id));
    }

    public void delete(String id) {
        this.requestRepository.findById(id)
                .orElseThrow(() -> new RequestNotFoundException(id));
        this.requestRepository.deleteById(id);
    }
}
