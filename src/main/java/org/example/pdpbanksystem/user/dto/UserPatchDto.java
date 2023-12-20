package org.example.pdpbanksystem.user.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserPatchDto {
    private String firstname;
    private String lastname;
    private String phoneNumber;
    private String email;
    private String password;

}
