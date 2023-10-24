package com.dadvani.libraryApp.service.secService;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class CustomUserDetails implements UserDetails {

    private String username;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;
    private String fullname;
    private String role;
    private boolean membershipStatus;


    public CustomUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities,
                             String fullname,String role,boolean membershipStatus) {

        this.username = username;
        this.password = password;
        this.authorities = authorities;
        this.fullname = fullname;
        this.role = role;
        this.membershipStatus = membershipStatus;
    }

    public String getFullname() {
        return fullname;
    }

    public String getRole() {
        return role;
    }

    public boolean isMembershipStatus() {
        return membershipStatus;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {

        return password;
    }

    @Override
    public String getUsername() {
        // TODO Auto-generated method stub
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isEnabled() {
        // TODO Auto-generated method stub
        return true;
    }
}
