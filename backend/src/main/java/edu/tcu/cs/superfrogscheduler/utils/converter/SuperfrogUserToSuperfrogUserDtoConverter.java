package edu.tcu.cs.superfrogscheduler.utils.converter;

import edu.tcu.cs.superfrogscheduler.user.SuperfrogUser;
import edu.tcu.cs.superfrogscheduler.utils.dto.SuperfrogUserDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class SuperfrogUserToSuperfrogUserDtoConverter implements Converter<SuperfrogUser, SuperfrogUserDto> {
    @Override
    public SuperfrogUserDto convert(SuperfrogUser source) {
        final SuperfrogUserDto userDto = new SuperfrogUserDto(
                source.getId(),
                source.getUsername(),
                source.isEnabled(),
                source.getRoles());
        return userDto;
    }
}
