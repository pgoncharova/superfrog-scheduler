package edu.tcu.cs.superfrogscheduler.utils.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record RequestDto (String id,
                            @NotEmpty(message = "first name is required.")
                            String firstName,
                            @NotEmpty(message = "last name is required.")
                            String lastName,
                            @NotEmpty(message = "phone number is required.")
                            String phoneNumber,
                            @NotEmpty(message = "email is required.")
                            String email,
                          @NotEmpty(message = "event type is required.")
                          String eventType,
                          @NotEmpty(message = "event title is required.")
                          String eventTitle,
                          @NotEmpty(message = "organization name is required.")
                          String organizationName,
                          @NotEmpty(message = "event address is required.")
                          String eventAddress,
                          @NotNull(message = "whether on-campus is required.")
                          boolean isOnCampus,
                          @NotEmpty(message = "special instructions are required.")
                          String specialInstructions,
                          @NotEmpty(message = "benefits description is required.")
                          String benefitsDescription,
                          @NotEmpty(message = "sponsor description is required.")
                          String sponsorDescription,
                          @NotEmpty(message = "detailed description is required.")
                          String detailedDescription) {
}
