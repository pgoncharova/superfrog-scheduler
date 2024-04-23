package edu.tcu.cs.superfrogscheduler.customer;

import edu.tcu.cs.superfrogscheduler.system.Result;
import edu.tcu.cs.superfrogscheduler.system.StatusCode;
import edu.tcu.cs.superfrogscheduler.utils.converter.CustomerDtoToCustomerConverter;
import edu.tcu.cs.superfrogscheduler.utils.converter.CustomerToCustomerDtoConverter;
import edu.tcu.cs.superfrogscheduler.utils.dto.CustomerDto;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;
    private final CustomerDtoToCustomerConverter customerDtoToCustomerConverter;
    private final CustomerToCustomerDtoConverter customerToCustomerDtoConverter;

    public CustomerController(CustomerService customerService, CustomerDtoToCustomerConverter customerDtoToCustomerConverter, CustomerToCustomerDtoConverter customerToCustomerDtoConverter) {
        this.customerService = customerService;
        this.customerDtoToCustomerConverter = customerDtoToCustomerConverter;
        this.customerToCustomerDtoConverter = customerToCustomerDtoConverter;
    }

    @GetMapping
    public Result findAllCustomers() {
        List<Customer> foundCustomers = this.customerService.findAll();

        // convert foundCustomers to a list of CustomerDtos
        List<CustomerDto> customerDtos = foundCustomers.stream()
                .map(this.customerToCustomerDtoConverter::convert)
                .collect(Collectors.toList());
        return new Result(true, StatusCode.SUCCESS, "Find All Success", customerDtos);
    }

    @GetMapping("/{customerId}")
    public Result getCustomerById(@PathVariable Long customerId) {
        Customer foundCustomer = this.customerService.findById(customerId);
        CustomerDto customerDto = this.customerToCustomerDtoConverter.convert(foundCustomer);
        return new Result(true, StatusCode.SUCCESS, "Find One Success", customerDto);
    }

    @PostMapping
    public Result addCustomer(@Valid @RequestBody CustomerDto customerDto) {
        Customer newCustomer = this.customerDtoToCustomerConverter.convert(customerDto);
        Customer savedCustomer = this.customerService.save(newCustomer);
        CustomerDto savedCustomerDto = this.customerToCustomerDtoConverter.convert(savedCustomer);
        return new Result(true, StatusCode.SUCCESS, "Add Success", savedCustomerDto);
    }


    @PutMapping("/{customerId}")
    public Result updateCustomer(@PathVariable Long customerId, @Valid @RequestBody CustomerDto customerDto) {
        Customer update = this.customerDtoToCustomerConverter.convert(customerDto);
        Customer updatedCustomer = this.customerService.update(customerId, update);
        CustomerDto updatedCustomerDto = this.customerToCustomerDtoConverter.convert(updatedCustomer);
        return new Result(true, StatusCode.SUCCESS, "Update Success", updatedCustomerDto);
    }

    @DeleteMapping("/{customerId}")
    public Result deleteCustomer(@PathVariable Long customerId) {
        this.customerService.delete(customerId);
        return new Result(true, StatusCode.SUCCESS, "Delete Success");
    }

    @PutMapping("/{customerId}/requests/{requestId}")
    public Result assignRequest(@PathVariable Long customerId, @PathVariable String requestId) {
        this.customerService.assignRequest(customerId, requestId);
        return new Result(true, StatusCode.SUCCESS, "Request Assignment Success");
    }

}
