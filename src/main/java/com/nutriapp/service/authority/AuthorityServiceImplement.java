package com.nutriapp.service.authority;

import com.nutriapp.auth.User;
import com.nutriapp.domain.Authority;
import com.nutriapp.repository.AuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorityServiceImplement implements AuthorityService {

    @Autowired
    private AuthorityRepository authorityRepository;

    public Authority save(Authority authority) {
        authorityRepository.findFirstByRole(authority.getRole()).ifPresent(authority1 -> authority.setId(authority1.getId()));
        return authorityRepository.save(authority);
    }

    @Override
    public Boolean exists(Authority authority) {
        return authorityRepository.exists(Example.of(authority));
    }

    @Override
    public List<Authority> findAuthoritiesByUser(User user) {
        return authorityRepository.findAuthoritiesByUser(user.getId());
    }
}
