package com.zimomo.lab4.entity.security;

import com.zimomo.lab4.entity.roles.LoginUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class LoginUserDetails implements UserDetails {
    private LoginUser loginUser;
    private Collection<? extends  GrantedAuthority> authorities;

    public LoginUserDetails(LoginUser loginUser,Collection<? extends  GrantedAuthority> authorities){
        super();
        this.loginUser=loginUser;
        this.authorities=authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return this.loginUser.getPassword();
    }

    @Override
    public String getUsername() {
        return this.loginUser.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
