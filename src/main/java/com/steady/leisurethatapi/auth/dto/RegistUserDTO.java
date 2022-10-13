package com.steady.leisurethatapi.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistUserDTO {

    private String username;
    private String password;

    @Email
    private String email;
    private String name;
    private String phone;
    private String delivery;
}
