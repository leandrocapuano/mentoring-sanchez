package br.com.orbitall.mentoring.canonicals;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record CardInput(
        @NotBlank(message = "Full name cannot be null or empty")
        @Size(min = 1, max = 255, message = "Full name must be between 1 and 255 characters")
        String fullName,
        @NotBlank(message = "Number cannot be null or empty")
        String number,
        @NotBlank(message = "Number cannot be null or empty")
        @Size(min = 1, max = 4, message = "Cvv2 must be between 1 and 4 characters")
        String cvv2,
        @NotBlank(message = "Valid thru cannot be null or empty")
        String validThru) {
}
