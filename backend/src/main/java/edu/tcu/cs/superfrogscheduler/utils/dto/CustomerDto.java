package edu.tcu.cs.superfrogscheduler.utils.dto;

import jakarta.validation.constraints.NotEmpty;

public record CustomerDto(Long id,
                          String firstName,
                          String lastName,
                          @NotEmpty(message = "email is required.")
                          String email,
                          String phoneNumber,
                          Integer numberOfRequests) {
}
