package org.zapovednik.groupservice.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponsibleSpecialistRequestDto {

    @NotEmpty(message = "Responsible specialist name is required")
    @Size(max = 50, message = "Responsible specialist name must not exceed 50 characters")
    private String responsibleSpecialistName;

    @NotEmpty(message = "Responsible specialist phone number is required")
    @Size(min = 9, max = 9, message = "Responsible specialist phone number must be exactly 9 characters")
    @Pattern(regexp = "^[0-9]{9}$", message = "Responsible specialist phone number must contain exactly 12 digits")
    private String responsibleSpecialistPhoneNumber;
}