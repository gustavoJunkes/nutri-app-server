package com.nutriapp.resource;

import com.nutriapp.auth.UserService;
import com.nutriapp.dto.UserDto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/user")
@AllArgsConstructor
public class UserResource {

    UserService userService;

    @PostMapping("/save")
    public ResponseEntity<UserDto> save(
           @RequestBody UserDto userDto
            ) {
        userDto = userService.save(userDto);
        return ResponseEntity.ok(userDto);
    }


}
 