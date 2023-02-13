package com.nutriapp.dto.auth;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Builder
public class AuthenticateDto {
    private String username;
    private String password;
}
