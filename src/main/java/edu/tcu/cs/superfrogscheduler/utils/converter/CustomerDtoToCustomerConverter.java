package edu.tcu.cs.superfrogscheduler.utils.converter;

import edu.tcu.cs.superfrogscheduler.customer.Customer;
import edu.tcu.cs.superfrogscheduler.utils.dto.CustomerDto;
import org.springframework.core.convert.converter.Converter;

public class CustomerDtoToCustomerConverter implements Converter<CustomerDto, Customer> {

    @Override
    public Customer convert(CustomerDto source) {
        Customer customer = new Customer();
        customer.setId(source.id());
        customer.setCustomername(source.customername());
        customer.setEnabled(source.active());
        customer.setRoles(source.roles());
        return customer;
    }
}
