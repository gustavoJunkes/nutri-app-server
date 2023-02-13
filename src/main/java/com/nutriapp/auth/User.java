package com.nutriapp.auth;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nutriapp.domain.Authority;
import com.nutriapp.domain.WeeklyMenu;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;

import java.util.*;


//@Value
@Builder
@Entity(name = "tb_user")
@NoArgsConstructor
@AllArgsConstructor
//@Getter
@Setter
public class User implements UserDetails {
    private static final long serialVersionUID = 2396654715019746670L;

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @Column(unique = true)
    private String username;
    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Authority> authorities;

    public User(UUID id, String username, String password){
        this.id = id;
        this.username = username;
        this.password = password;
    }

    // TODO: 04/01/2023 implement authorities logic

    @JsonIgnore
    @Override
    public List<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
//        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return password;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    public UUID getId(){
        return id;
    }

    @Deprecated
    public User addRole(String... roles) {
        List<GrantedAuthority> authorities = new ArrayList(roles.length);
        String[] var3 = roles;
        int var4 = roles.length;

        for(int var5 = 0; var5 < var4; ++var5) {
            String role = var3[var5];
            Assert.isTrue(!role.startsWith("ROLE_"), () -> {
                return role + " cannot start with ROLE_ (it is automatically added)";
            });
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
        }
//        this.authorities = authorities;
        return this;
    }

    public User addAuthority(Authority authority) {
        this.authorities.add(authority);
        return this;
    }

}