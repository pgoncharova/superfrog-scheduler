package edu.tcu.cs.superfrogscheduler.customer;

import edu.tcu.cs.superfrogscheduler.customer.Customer;
import edu.tcu.cs.superfrogscheduler.customer.CustomerService;
import edu.tcu.cs.superfrogscheduler.request.EventRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;


import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerService.findAllCustomers();
    }

    @PostMapping
    public Customer createCustomer(@RequestBody Customer customer) {
        return customerService.saveCustomer(customer);
    }

    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable Long id) {
        return customerService.findCustomerById(id);
    }

    @PostMapping("/{customerId}/requests")
    public EventRequest submitRequest(@PathVariable Long customerId, @RequestBody EventRequest requestDetails) {
        return customerService.submitRequest(customerId, requestDetails);
    }

    @Autowired
    private CustomerService customerService;

    // Endpoint to submit a new event request
    @PostMapping("/{customerId}/events")
    public ResponseEntity<EventRequest> createEventRequest(@PathVariable Long customerId, @RequestBody EventRequest eventRequest) {
        EventRequest newEventRequest = customerService.createEventRequest(customerId, eventRequest);
        return new ResponseEntity<>(newEventRequest, HttpStatus.CREATED);
    }

    @PatchMapping("/requests/{requestId}")
    public ResponseEntity<EventRequest> updateRequest(@PathVariable Long requestId, @RequestBody EventRequest requestDetails) {
        EventRequest updatedRequest = customerService.editRequest(requestId, requestDetails);
        if (updatedRequest != null) {
            return ResponseEntity.ok(updatedRequest);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{customerId}/requests/{requestId}/cancel")
    public ResponseEntity<Void> cancelRequest(@PathVariable Long customerId, @PathVariable Long requestId) {
        boolean isCancelled = customerService.cancelRequest(requestId);
        if (isCancelled) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }



}
