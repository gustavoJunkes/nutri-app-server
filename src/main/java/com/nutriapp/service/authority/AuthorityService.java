package com.nutriapp.service.authority;

import com.nutriapp.auth.User;
import com.nutriapp.domain.Authority;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;

public interface AuthorityService {

    Authority save(Authority authority);

    Boolean exists(Authority authority);

    List<Authority> findAuthoritiesByUser(User user);

}
