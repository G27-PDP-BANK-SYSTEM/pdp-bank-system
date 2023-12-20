package org.example.pdpbanksystem.user.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserSignInDto {

    @NotBlank
    private String email;

    @NotBlank
    private String password;
}
