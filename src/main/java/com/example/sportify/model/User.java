package com.example.sportify.model;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Entity
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    private String password;
    private String email;
    private String role; // "USER", "ADMIN"

    // Getters & Setters
    public Long getId() {
         return id; 
    }
    public void setId(Long id) {
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

    public String getEmail() {
         return email; 
    }
    public void setEmail(String email) {
         this.email = email; 
    }

    public String getRole() {
         return role; 
    }
    public void setRole(String role) {
         this.role = role; 
    }

    // Spring Security - UserDetails implementation
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(role));
    }

    @Override public boolean isAccountNonExpired() {
         return true; 
    }
    @Override public boolean isAccountNonLocked() {
         return true; 
    }
    @Override public boolean isCredentialsNonExpired() {
         return true; 
    }
    @Override public boolean isEnabled() {
         return true; 
    }
}
