package com.codeventlk.helloshoemanagementsystem.dto;

import com.codeventlk.helloshoemanagementsystem.Enum.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public class UserDTO implements SuperDTO{
    @NotNull(message = "Email cannot be null")
    @Email
    private String email;
    @NotNull(message = "Password cannot be null")
    private String password;
    @NotNull(message = "Role cannot be null")
    private Role role;
}
