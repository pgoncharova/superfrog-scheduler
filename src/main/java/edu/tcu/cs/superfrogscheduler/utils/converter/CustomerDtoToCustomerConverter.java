package edu.tcu.cs.superfrogscheduler.utils.converter;

import edu.tcu.cs.superfrogscheduler.customer.Customer;
import edu.tcu.cs.superfrogscheduler.utils.dto.CustomerDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CustomerDtoToCustomerConverter implements Converter<CustomerDto, Customer> {

    @Override
    public Customer convert(CustomerDto source) {
        Customer customer = new Customer();
        customer.setId(source.id());
        customer.setFirstName(source.firstName());
        customer.setLastName(source.lastName());
        customer.setEmail(source.email());
        customer.setPhoneNumber(source.phoneNumber());
        return customer;
    }
}
