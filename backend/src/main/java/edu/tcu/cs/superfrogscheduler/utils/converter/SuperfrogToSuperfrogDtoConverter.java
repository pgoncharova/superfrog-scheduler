package edu.tcu.cs.superfrogscheduler.utils.converter;

import edu.tcu.cs.superfrogscheduler.superfrog.Superfrog;
import edu.tcu.cs.superfrogscheduler.utils.dto.SuperfrogDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class SuperfrogToSuperfrogDtoConverter implements Converter<Superfrog, SuperfrogDto> {

    @Override
    public SuperfrogDto convert(Superfrog source) {
        SuperfrogDto superfrogDto = new SuperfrogDto(source.getFirstName(),
                source.getLastName(), source.getPhoneNumber(),
                source.getPhysicalAddress(), source.getEmail());
        return superfrogDto;
    }
}
