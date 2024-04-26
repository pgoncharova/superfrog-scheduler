package edu.tcu.cs.superfrogscheduler.utils.converter;

import edu.tcu.cs.superfrogscheduler.user.SuperfrogUser;
import edu.tcu.cs.superfrogscheduler.utils.dto.SuperfrogUserDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class SuperfrogUserDtoToSuperfrogUserConverter implements Converter<SuperfrogUserDto, SuperfrogUser> {
    @Override
    public SuperfrogUser convert(SuperfrogUserDto source) {
        SuperfrogUser superfrogUser = new SuperfrogUser();
        superfrogUser.setUsername(source.username());
        superfrogUser.setEnabled(source.enabled());
        superfrogUser.setRoles(source.roles());
        return superfrogUser;
    }
}