package edu.tcu.cs.superfrogscheduler.customer;

import edu.tcu.cs.superfrogscheduler.customer.Customer;
import edu.tcu.cs.superfrogscheduler.request.Request;
import edu.tcu.cs.superfrogscheduler.customer.CustomerRepository;
import edu.tcu.cs.superfrogscheduler.request.RequestRepository;
import edu.tcu.cs.superfrogscheduler.system.exception.ObjectNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.List;

@Service
@Transactional
public class CustomerService {

    private final CustomerRepository customerRepository;

    private final RequestRepository requestRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, RequestRepository requestRepository) {
        this.customerRepository = customerRepository;
        this.requestRepository = requestRepository;
    }

    public List<Customer> findAll() {
        return this.customerRepository.findAll();
    }

    public Customer findById(Long customerId) {
        return this.customerRepository.findById(customerId)
                .orElseThrow(() -> new ObjectNotFoundException("customer", customerId));
    }


    public Customer save(Customer newCustomer) {
        return this.customerRepository.save(newCustomer);
    }

    public Customer update(Long customerId, Customer update) {
        return this.customerRepository.findById(customerId)
                .map(oldCustomer -> {
                    oldCustomer.setFirstName(update.getFirstName());
                    oldCustomer.setLastName(update.getLastName());
                    oldCustomer.setEmail(update.getEmail());
                    oldCustomer.setPhoneNumber(update.getPhoneNumber());
                    return this.customerRepository.save(oldCustomer);
                })
                .orElseThrow(() -> new ObjectNotFoundException("customer", customerId));
    }

    public void delete(Long customerId) {
        Customer customerToBeDeleted = this.customerRepository.findById(customerId)
                .orElseThrow(() -> new ObjectNotFoundException("customer", customerId));

        // Unassign requests before deletion (remove foreign keys)
        customerToBeDeleted.removeAllRequests();
        this.customerRepository.deleteById(customerId);
    }

    public void assignRequest(Long customerId, String requestId) {
        // find this request by id from DB
        Request requestToBeAssigned = this.requestRepository.findById(requestId)
                .orElseThrow(() -> new ObjectNotFoundException("request", requestId));

        // find this customer by id from DB
        Customer customer = this.customerRepository.findById(customerId)
                .orElseThrow(() -> new ObjectNotFoundException("customer", customerId));

        // request assignment
        if (requestToBeAssigned.getOwner() != null) {
            requestToBeAssigned.getOwner().removeRequest(requestToBeAssigned);
        }
        customer.addRequest(requestToBeAssigned);
    }
}
