package edu.tcu.cs.superfrogscheduler.superfrog;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Transactional
public class SuperfrogService {
    // VARIABLES:
    private final SuperfrogRepository superfrogRepository;

    // CONSTRUCTOR:
    public SuperfrogService(SuperfrogRepository superfrogRepository) {
        this.superfrogRepository = superfrogRepository;
    }

    // METHODS:
    public Superfrog findById(String email) {
        return this.superfrogRepository.findById(email)
                .orElseThrow(() -> new SuperfrogNotFoundException(email));
    }

    public List<Superfrog> findAll() {
        return this.superfrogRepository.findAll();
    }

    public Superfrog save(Superfrog newSuperfrog) {
        return this.superfrogRepository.save(newSuperfrog);
    }

    public Superfrog update(String email, Superfrog update) {
        return this.superfrogRepository.findById(email)
                .map(oldSuperfrog -> {
                    oldSuperfrog.setFirstName(update.getFirstName());
                    oldSuperfrog.setLastName(update.getLastName());
                    oldSuperfrog.setPhoneNumber(update.getPhoneNumber());
                    oldSuperfrog.setPhysicalAddress(update.getPhysicalAddress());
                    oldSuperfrog.setEmail(update.getEmail());
                    return this.superfrogRepository.save(oldSuperfrog);
                })
                .orElseThrow(() -> new SuperfrogNotFoundException(email));
    }

    public void delete(String email) {
        this.superfrogRepository.findById(email)
                .orElseThrow(() -> new SuperfrogNotFoundException(email));
        this.superfrogRepository.deleteById(email);
    }
}
