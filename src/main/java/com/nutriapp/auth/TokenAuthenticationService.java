//package com.nutriapp.auth;
//
//import com.google.common.collect.ImmutableMap;
//
//import lombok.AllArgsConstructor;
//import lombok.experimental.FieldDefaults;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.Objects;
//import java.util.Optional;
//
//import static lombok.AccessLevel.PACKAGE;
//import static lombok.AccessLevel.PRIVATE;
//
//@Service
//final class TokenAuthenticationService implements UserAuthenticationService {
//
//    TokenService tokenService;
//
//    @Autowired
//    public void setTokenService(TokenService tokenService) {
//        this.tokenService = tokenService;
//    }
//    private UserService users;
//
//    @Autowired
//    public void setUsers(UserService users) {
//        this.users = users;
//    }
//
//    @Override
//    public Optional<String> login(final String username, final String password) {
//        return users
//                .findByUsername(username)
//                .filter(user -> Objects.equals(password, user.getPassword()))
//                .map(user -> tokenService.generateToken(new User(null, username, password)));
//    }
//
////    @Override
////    public Optional<User> findByToken(final String token) {
////        System.out.println("$$$$$$$$$$$$$$$$$$$$ token: " + token);
////        return Optional
////                .of(tokenService.verify(token))
////                .map(map -> map.get("username"))
////                .flatMap(users::findByUsername);
////    }
//
//    @Override
//    public void logout(final User user) {
//    }
//}