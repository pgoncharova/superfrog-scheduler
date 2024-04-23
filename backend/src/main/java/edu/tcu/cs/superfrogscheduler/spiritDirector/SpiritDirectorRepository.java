package edu.tcu.cs.superfrogscheduler.spiritDirector;

import edu.tcu.cs.superfrogscheduler.request.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpiritDirectorRepository extends JpaRepository<SpiritDirector, String> {
    // You can define custom query methods if needed
    //Request findRequestById(String requestId);
    //Request saveRequest(Request request);

    // JpaRepository already provides methods like save(), findOne(), findAll(), etc.
}

