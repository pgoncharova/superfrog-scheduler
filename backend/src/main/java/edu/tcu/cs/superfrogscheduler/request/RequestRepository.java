package edu.tcu.cs.superfrogscheduler.request;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestRepository extends JpaRepository<Request, String> {
    List<Request> findByFirstName(String firstName);
    List<Request> findByLastName(String lastName);
    List<Request> findByEventType(String eventType);
    List<Request> findByEventTitle(String eventTitle);
    List<Request> findByOrganizationName(String organizationName);
    List<Request> findByStatus(RequestStatus requestStatus);
}
