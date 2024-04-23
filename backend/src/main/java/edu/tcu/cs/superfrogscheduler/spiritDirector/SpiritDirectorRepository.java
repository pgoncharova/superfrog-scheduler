package edu.tcu.cs.superfrogscheduler.spiritDirector;

import edu.tcu.cs.superfrogscheduler.request.EventRequest;
import edu.tcu.cs.superfrogscheduler.superfrog.Superfrog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpiritDirectorRepository extends JpaRepository<EventRequest, String> {
    // You can define custom query methods if needed
    EventRequest findById(String requestId);

    // JpaRepository already provides methods like save(), findOne(), findAll(), etc.
}

