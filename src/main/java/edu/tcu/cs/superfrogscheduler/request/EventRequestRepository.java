package edu.tcu.cs.superfrogscheduler.request;

import edu.tcu.cs.superfrogscheduler.request.EventRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRequestRepository extends JpaRepository<EventRequest, Long> {
}
