package edu.tcu.cs.superfrogscheduler.utils.converter;

import edu.tcu.cs.superfrogscheduler.customer.Customer;
import edu.tcu.cs.superfrogscheduler.utils.dto.CustomerDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CustomerToCustomerDtoConverter implements Converter<Customer, CustomerDto> {

    @Override
    public CustomerDto convert(Customer source) {
        CustomerDto customerDto = new CustomerDto(source.getId(),
                source.getFirstName(), source.getLastName(),
                source.getEmail(), source.getPhoneNumber(),
                source.getNumberOfRequests());
        return customerDto;
    }
}
