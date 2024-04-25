package edu.tcu.cs.superfrogscheduler.utils.dto;

import jakarta.validation.constraints.NotEmpty;

public record SuperfrogUserDto(Integer id,
                      @NotEmpty(message = "username is required.")
                      String username,
                      boolean enabled,
                      @NotEmpty(message = "roles are required.")
                      String roles) {
}
