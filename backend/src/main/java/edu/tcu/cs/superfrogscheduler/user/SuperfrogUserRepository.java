package edu.tcu.cs.superfrogscheduler.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SuperfrogUserRepository extends JpaRepository<SuperfrogUser, Integer> {
    Optional<SuperfrogUser> findByUsername(String username);
}
