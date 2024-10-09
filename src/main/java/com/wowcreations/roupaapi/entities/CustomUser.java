package com.wowcreations.roupaapi.entities;

import java.util.*;

import com.wowcreations.roupaapi.entities.enums.Role;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
public class CustomUser implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String email;
    private String name;
    private String username;
    private String hash;
    private Role role;

    // ! IMPLEMENTAR RELACOES COM O CARRINHO DE COMPRAS E OUTRAS TABELAS DEPOIS

    public CustomUser() {}

    public CustomUser(String email, String name, String username, String hash) {
        this.email = email;
        this.name = name;
        this.username = username;
        this.hash = hash;
        this.role = Role.USER;
    }

    public UUID getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getHash() {
        return this.hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.role == Role.ADMIN)
            return List.of(new SimpleGrantedAuthority(Role.ADMIN.getRole()), new SimpleGrantedAuthority(Role.USER.getRole()));

        return List.of(new SimpleGrantedAuthority(Role.USER.getRole()));
    }

    @Override
    public String getPassword() {
        return this.hash;
    }

    @Override
    public String getUsername() {
        return null;
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

    public int hashCode() {
        return Objects.hash(this.id, this.name, this.username, this.hash);
    }

    public boolean equals(Object obj) {
        if(obj == this)
            return true;

        if(!(obj instanceof CustomUser))
            return false;

        CustomUser user = (CustomUser) obj;

        return Objects.equals(user.id, this.id) && Objects.equals(user.name, this.name) && Objects.equals(user.username, this.username) && Objects.equals(user.hash, this.hash);
    }
}
