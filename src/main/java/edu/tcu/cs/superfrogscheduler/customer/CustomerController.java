package edu.tcu.cs.superfrogscheduler.customer;

import edu.tcu.cs.superfrogscheduler.customer.Customer;
import edu.tcu.cs.superfrogscheduler.customer.CustomerService;
import edu.tcu.cs.superfrogscheduler.request.EventRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

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

}
