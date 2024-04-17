package edu.tcu.cs.superfrogscheduler.utils.converter;

import edu.tcu.cs.superfrogscheduler.request.Request;
import edu.tcu.cs.superfrogscheduler.utils.dto.RequestDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class RequestToRequestDtoConverter implements Converter<Request, RequestDto> {

    @Override
    public RequestDto convert(Request source) {
        RequestDto requestDto = new RequestDto(source.getId(),
                source.getFirstName(), source.getLastName(), source.getPhoneNumber(),
                source.getEmail(), source.getEventType(), source.getEventTitle(),
                source.getOrganizationName(), source.getEventAddress(), source.isOnCampus(),
                source.getSpecialInstructions(), source.getBenefitsDescription(), source.getSponsorDescription(),
                source.getDetailedDescription());
        return requestDto;
    }
}
