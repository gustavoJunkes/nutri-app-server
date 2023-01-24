package com.nutriapp.dto;

import com.nutriapp.auth.User;
import com.nutriapp.domain.Authority;
import lombok.*;

import java.util.List;

//@Getter
//@Setter
@Builder
@AllArgsConstructor
@Data
public class UserDto {
    private String id;
    private String username;
    private String password;

    private List<Authority> authorities;

    public UserDto() {}

    public UserDto(String id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public UserDto(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
