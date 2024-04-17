package edu.tcu.cs.superfrogscheduler.utils.converter;

import edu.tcu.cs.superfrogscheduler.request.Request;
import edu.tcu.cs.superfrogscheduler.request.RequestController;
import edu.tcu.cs.superfrogscheduler.utils.dto.RequestDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class RequestDtoToRequestConverter implements Converter<RequestDto, Request> {

    @Override
    public Request convert(RequestDto source) {
        Request request = new Request();
        request.setId(source.id());
        request.setFirstName(source.firstName());
        request.setLastName(source.lastName());
        request.setPhoneNumber(source.phoneNumber());
        request.setEmail(source.email());
        request.setEventType(source.eventType());
        request.setEventTitle(source.eventTitle());
        request.setOrganizationName(source.organizationName());
        request.setEventAddress(source.eventAddress());
        request.setOnCampus(source.isOnCampus());
        request.setSpecialInstructions(source.specialInstructions());
        request.setBenefitsDescription(source.benefitsDescription());
        request.setSponsorDescription(source.sponsorDescription());
        request.setDetailedDescription(source.detailedDescription());
        return request;
    }
}
