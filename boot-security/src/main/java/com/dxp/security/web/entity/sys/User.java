package com.dxp.security.web.entity.sys;

import com.dxp.security.conf.core.StringGrantedAuthority;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 账号
 *
 * @author carzy
 * @date 2020/8/4
 */
public class User implements UserDetails {

    private Long id;

    private String username;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    private boolean enabled;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<StringGrantedAuthority> authorities = new ArrayList<>();

    private List<Role> roles;

    private List<Authorization> authorizations;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setAuthorities(List<StringGrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.getAuthorities().addAll(roles.stream().map(r -> {
            StringGrantedAuthority authority = new StringGrantedAuthority();
            authority.setRole(r.getName());
            return authority;
        }).collect(Collectors.toSet()));
        this.roles = roles;
    }

    public List<Authorization> getAuthorizations() {
        return authorizations;
    }

    public void setAuthorizations(List<Authorization> authorizations) {
        this.getAuthorities().addAll(authorizations.stream().map(r -> {
            StringGrantedAuthority authority = new StringGrantedAuthority();
            authority.setName(r.getName());
            return authority;
        }).collect(Collectors.toSet()));
        this.authorizations = authorizations;
    }

    @Override
    public Collection<StringGrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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
        return enabled;
    }
}
