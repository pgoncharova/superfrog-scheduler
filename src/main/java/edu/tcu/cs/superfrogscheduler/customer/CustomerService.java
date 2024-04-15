package edu.tcu.cs.superfrogscheduler.customer;

import edu.tcu.cs.superfrogscheduler.customer.Customer;
import edu.tcu.cs.superfrogscheduler.request.EventRequest;
import edu.tcu.cs.superfrogscheduler.customer.CustomerRepository;
import edu.tcu.cs.superfrogscheduler.request.EventRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository repository;

    @Autowired
    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    public List<Customer> findAllCustomers() {
        return repository.findAll();
    }

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private EventRequestRepository eventRequestRepository;

    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer findCustomerById(Long id) {
        return customerRepository.findById(id).orElse(null);
    }

    public EventRequest submitRequest(Long customerId, EventRequest requestDetails) {
        Optional<Customer> customer = customerRepository.findById(customerId);
        if(customer.isPresent()) {
            requestDetails.setCustomer(customer.get());
            return eventRequestRepository.save(requestDetails);
        }
        return null; // Or throw an exception if preferred
    }

    public EventRequest editRequest(Long requestId, EventRequest updatedDetails) {
        Optional<EventRequest> existingRequest = eventRequestRepository.findById(requestId);
        if(existingRequest.isPresent()) {
            EventRequest requestToUpdate = existingRequest.get();
            // Assuming you have setters in EventRequest for fields that can be updated
            requestToUpdate.setEventDetails(updatedDetails.getEventDetails()); // example field update
            return eventRequestRepository.save(requestToUpdate);
        }
        return null; // Or throw an exception if preferred
    }

    public boolean cancelRequest(Long requestId) {
        Optional<EventRequest> request = eventRequestRepository.findById(requestId);
        if(request.isPresent()) {
            eventRequestRepository.deleteById(requestId);
            return true;
        }
        return false; // Or throw an exception if preferred
    }

    public EventRequest createEventRequest(Long customerId, EventRequest eventRequest) {

        // Save the event request after setting the customer
        Customer customer = customerRepository.findById(customerId).orElseThrow(/* throw a suitable exception */);
        customer.addEventRequest(eventRequest);
        return eventRequestRepository.save(eventRequest);
    }
}
