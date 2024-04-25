package edu.tcu.cs.superfrogscheduler.security;

import edu.tcu.cs.superfrogscheduler.user.MyUserPrincipal;
import edu.tcu.cs.superfrogscheduler.user.SuperfrogUser;
import edu.tcu.cs.superfrogscheduler.utils.converter.SuperfrogUserToSuperfrogUserDtoConverter;
import edu.tcu.cs.superfrogscheduler.utils.dto.SuperfrogUserDto;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {

    private final JwtProvider jwtProvider;

    private final SuperfrogUserToSuperfrogUserDtoConverter superfrogUserToSuperfrogUserDtoConverter;

    public AuthService(JwtProvider jwtProvider, SuperfrogUserToSuperfrogUserDtoConverter superfrogUserToSuperfrogUserDtoConverter) {
        this.jwtProvider = jwtProvider;
        this.superfrogUserToSuperfrogUserDtoConverter = superfrogUserToSuperfrogUserDtoConverter;
    }

    public Map<String, Object> createLoginInfo(Authentication authentication) {
        // Create user info.
        MyUserPrincipal principal = (MyUserPrincipal)authentication.getPrincipal();
        SuperfrogUser superfrogUser = principal.getUser();
        SuperfrogUserDto userDto = this.superfrogUserToSuperfrogUserDtoConverter.convert(superfrogUser);

        // Create a JWT.
        String token = this.jwtProvider.createToken(authentication);

        Map<String, Object> loginResultMap = new HashMap<>();

        loginResultMap.put("userInfo", userDto);
        loginResultMap.put("token", token);

        return loginResultMap;
    }
}