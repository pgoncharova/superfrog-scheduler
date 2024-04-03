package edu.tcu.cs.superfrogscheduler.customer;

import edu.tcu.cs.superfrogscheduler.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
