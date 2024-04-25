package edu.tcu.cs.superfrogscheduler.utils.converter;

import edu.tcu.cs.superfrogscheduler.request.Request;
import edu.tcu.cs.superfrogscheduler.utils.dto.RequestDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class RequestToRequestDtoConverter implements Converter<Request, RequestDto> {

    private final SuperfrogToSuperfrogDtoConverter superfrogToSuperfrogDtoConverter;
    private final CustomerToCustomerDtoConverter customerToCustomerDtoConverter;

    public RequestToRequestDtoConverter(SuperfrogToSuperfrogDtoConverter superfrogToSuperfrogDtoConverter, CustomerToCustomerDtoConverter customerToCustomerDtoConverter) {
        this.superfrogToSuperfrogDtoConverter = superfrogToSuperfrogDtoConverter;
        this.customerToCustomerDtoConverter = customerToCustomerDtoConverter;
    }

    @Override
    public RequestDto convert(Request source) {
        RequestDto requestDto = new RequestDto(source.getId(),
                source.getFirstName(), source.getLastName(), source.getPhoneNumber(),
                source.getEmail(), source.getEventType(), source.getEventTitle(),
                source.getOrganizationName(), source.getEventAddress(), source.isOnCampus(),
                source.getSpecialInstructions(), source.getBenefitsDescription(), source.getSponsorDescription(),
                source.getDetailedDescription(),
                source.getOwner() != null ? this.customerToCustomerDtoConverter.convert(source.getOwner()) : null,
                source.getSuperfrog() != null ? this.superfrogToSuperfrogDtoConverter.convert(source.getSuperfrog()) : null);
        return requestDto;
    }
}
