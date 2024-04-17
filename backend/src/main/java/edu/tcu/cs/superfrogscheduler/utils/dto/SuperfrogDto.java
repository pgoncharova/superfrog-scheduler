package edu.tcu.cs.superfrogscheduler.utils.dto;

import jakarta.validation.constraints.NotEmpty;

public record SuperfrogDto (@NotEmpty(message = "first name is required.")
                            String firstName,
                            @NotEmpty(message = "last name is required.")
                            String lastName,
                            @NotEmpty(message = "phone number is required.")
                            String phoneNumber,
                            @NotEmpty(message = "physical address is required.")
                            String physicalAddress,
                            @NotEmpty(message = "email is required.")
                            String email) {
}
