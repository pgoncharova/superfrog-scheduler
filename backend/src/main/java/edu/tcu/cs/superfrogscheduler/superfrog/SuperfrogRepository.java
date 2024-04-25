package edu.tcu.cs.superfrogscheduler.superfrog;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SuperfrogRepository extends JpaRepository<Superfrog, String> {
    Superfrog findByEmail(String email);
}
