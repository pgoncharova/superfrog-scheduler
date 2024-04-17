package edu.tcu.cs.superfrogscheduler.utils.converter;

import edu.tcu.cs.superfrogscheduler.superfrog.Superfrog;
import edu.tcu.cs.superfrogscheduler.superfrog.SuperfrogController;
import edu.tcu.cs.superfrogscheduler.utils.dto.SuperfrogDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class SuperfrogDtoToSuperfrogConverter implements Converter<SuperfrogDto, Superfrog> {

    @Override
    public Superfrog convert(SuperfrogDto source) {
        Superfrog superfrog = new Superfrog();
        superfrog.setFirstName(source.firstName());
        superfrog.setLastName(source.lastName());
        superfrog.setPhoneNumber(source.phoneNumber());
        superfrog.setPhysicalAddress(source.physicalAddress());
        superfrog.setEmail(source.email());
        return superfrog;
    }
}
