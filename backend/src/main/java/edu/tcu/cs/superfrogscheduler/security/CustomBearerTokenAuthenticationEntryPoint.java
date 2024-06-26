package edu.tcu.cs.superfrogscheduler.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;

/**
 * This class handles unsuccessful basic authentication.
 * We implement AuthenticationEntryPoint and then delegate the exception handler to HandlerExceptionResolver.
 */
@Component
public class CustomBearerTokenAuthenticationEntryPoint  implements AuthenticationEntryPoint {

    /*
     * This class handles unsuccessful JWT authentication.
     */

    private final HandlerExceptionResolver resolver;

    public CustomBearerTokenAuthenticationEntryPoint (@Qualifier("handlerExceptionResolver") HandlerExceptionResolver resolver) {
        this.resolver = resolver;
    }
    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        this.resolver.resolveException(request, response, null, authException);
    }
}