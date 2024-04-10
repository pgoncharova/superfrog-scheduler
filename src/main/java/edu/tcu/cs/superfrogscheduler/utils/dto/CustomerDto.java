package edu.tcu.cs.superfrogscheduler.utils.dto;

import jakarta.validation.constraints.NotEmpty;

public record CustomerDto(Integer id,
                      @NotEmpty(message = "email is required.")
                      String customername,
                      boolean active,
                      @NotEmpty(message = "roles are required.")
                      String roles) {
}
