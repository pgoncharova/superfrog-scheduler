package edu.tcu.cs.superfrogscheduler.customer;

import edu.tcu.cs.superfrogscheduler.system.exception.ObjectNotFoundException;
import edu.tcu.cs.superfrogscheduler.utils.IdWorker;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Transactional
public class CustomerService {
    // VARIABLES:
    private final CustomerRepository customerRepository;

    // CONSTRUCTOR:
    public CustomerService(CustomerRepository customerRepository, IdWorker idWorker) {
        this.customerRepository = customerRepository;
    }

    // METHODS:
    public Customer findById(Integer id) {
        return this.customerRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("customer", id));
    }

    public List<Customer> findAll() {
        return this.customerRepository.findAll();
    }

    public Customer save(Customer newCustomer) {
        return this.customerRepository.save(newCustomer);
    }

    public Customer update(Integer id, Customer update) {
        return this.customerRepository.findById(id)
                .map(oldCustomer -> {
                    oldCustomer.setCustomername(update.getCustomername());
                    oldCustomer.setPassword(update.getPassword());
                    oldCustomer.setEnabled(update.isEnabled());
                    return this.customerRepository.save(oldCustomer);
                })
                .orElseThrow(() -> new ObjectNotFoundException("customer", id));
    }

    public void delete(Integer id) {
        this.customerRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("customer", id));
        this.customerRepository.deleteById(id);
    }
}
