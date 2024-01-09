package org.example.pdpbanksystem.accNumber.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AccountNumberResponseDto {

    private Integer id;
    private String accountNumber;
}
