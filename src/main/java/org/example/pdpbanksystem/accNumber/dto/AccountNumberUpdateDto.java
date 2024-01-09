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
public class AccountNumberUpdateDto {

    @Length(max = 20, message = "Account number length should be at most 20 characters")
    @NotBlank
    @Pattern(regexp = "2020\\d{16}",message  = "Account number should start with '2020' and have 16 digits")
    private String accountNumber;

}
