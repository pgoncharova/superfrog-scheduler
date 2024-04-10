package edu.tcu.cs.superfrogscheduler.customer;

import edu.tcu.cs.superfrogscheduler.system.Result;
import edu.tcu.cs.superfrogscheduler.system.StatusCode;
import edu.tcu.cs.superfrogscheduler.utils.converter.*;
import edu.tcu.cs.superfrogscheduler.utils.dto.CustomerDto;
import edu.tcu.cs.superfrogscheduler.utils.dto.SuperfrogDto;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    private final CustomerService customerService;

    private final CustomerToCustomerDtoConverter customerToCustomerDtoConverter;

    private final CustomerDtoToCustomerConverter customerDtoToCustomerConverter;

    public CustomerController(CustomerService customerService,
                             CustomerToCustomerDtoConverter customerToCustomerDtoConverter,
                             CustomerDtoToCustomerConverter customerDtoToCustomerConverter) {
        this.customerService = customerService;
        this.customerToCustomerDtoConverter = customerToCustomerDtoConverter;
        this.customerDtoToCustomerConverter = customerDtoToCustomerConverter;
    }

    @GetMapping("/{customerId}")
    public Result findCustomerById(@PathVariable Integer customerId) {
        Customer foundCustomer = this.customerService.findById(customerId);
        CustomerDto customerDto = this.customerToCustomerDtoConverter.convert(foundCustomer);
        return new Result(true, StatusCode.SUCCESS, "Find One Success", customerDto);
    }

    @GetMapping
    public Result findAllCustomers() {
        List<Customer> foundCustomers = this.customerService.findAll();
        // Convert foundCustomers to a list of customerDtos
        List<CustomerDto> customerDtos = foundCustomers.stream()
                .map(this.customerToCustomerDtoConverter::convert)
                .collect(Collectors.toList());
        return new Result(true, StatusCode.SUCCESS, "Find All Success", customerDtos);
    }

    @PostMapping
    public Result addCustomer(@Valid @RequestBody CustomerDto customerDto) {
        // Convert SuperfrogDto to Superfrog
        Customer newCustomer = this.customerDtoToCustomerConverter.convert(customerDto);
        Customer savedCustomer = this.customerService.save(newCustomer);
        CustomerDto savedCustomerDto = this.customerToCustomerDtoConverter.convert(savedCustomer);
        return new Result(true,
                StatusCode.SUCCESS,
                "Add Success",
                savedCustomerDto);
    }

    @PutMapping("/{customerId}")
    public Result updateCustomer(@PathVariable Integer customerId, @Valid @RequestBody CustomerDto customerDto) {
        Customer update = this.customerDtoToCustomerConverter.convert(customerDto);
        Customer updatedCustomer = this.customerService.update(customerId, update);
        CustomerDto updatedCustomerDto = this.customerToCustomerDtoConverter.convert(updatedCustomer);
        return new Result(true, StatusCode.SUCCESS, "Update Success", updatedCustomerDto);

    }

    @DeleteMapping("/{customerId}")
    public Result deleteCustomer(@PathVariable Integer customerId) {
        this.customerService.delete(customerId);
        return new Result(true, StatusCode.SUCCESS, "Delete Success");
    }

}
