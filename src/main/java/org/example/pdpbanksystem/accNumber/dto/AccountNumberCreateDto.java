package org.example.pdpbanksystem.accNumber.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AccountNumberCreateDto {

    @Length(max = 20, message = "Account number length should be at most 20 characters")
    @NotBlank
    @Pattern(regexp = "\\d{20}", message = "Account number should consist of exactly 20 digits")
    private String accountNumber;
}
