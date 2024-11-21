package com.johnscodinglab.person.dto;

import com.johnscodinglab.person.enums.Gender;
import jakarta.validation.constraints.*;

public record NewPersonDto(
        @NotEmpty(message = "Name must not be null or empty") String name,
        @Min(value = 18, message = "Age must be greater than 18") @Max(value = 50, message = "Age must be below 50") Integer age,
        @NotNull(message = "Gender must not be null") Gender gender,
        @Email String email
) {
}
