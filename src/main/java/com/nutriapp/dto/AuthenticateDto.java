package com.nutriapp.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticateDto {
    private String username;
    private String password;
}
