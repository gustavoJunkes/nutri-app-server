package com.nutriapp.resource;

import com.nutriapp.auth.User;
import com.nutriapp.auth.UserService;
import com.nutriapp.dto.UserDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/user")
@AllArgsConstructor
public class UserResource {

    UserService userService;

    @PostMapping("/save")
    public ResponseEntity<User> save(
            @RequestParam("username") String username,
            @RequestParam("password") String password
            ) {

        User user = User.builder()
                .username(username)
                .password(password)
                .build();

        user = userService.save(user);

        System.out.println(user.getUsername());

        return ResponseEntity.ok(user);
    }


}
 