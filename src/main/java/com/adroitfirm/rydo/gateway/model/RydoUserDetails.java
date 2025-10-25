package com.adroitfirm.rydo.gateway.model;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.adroitfirm.rydo.dto.UserDto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RydoUserDetails implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4895115860791128746L;
	private final UserDto user;

    public RydoUserDetails(UserDto user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(user.getRole()));
    }

    @Override
    public String getPassword() {
        return null; // not used, since we use OTP
    }

    @Override
    public String getUsername() {
        return user.getPhone();
    }

    @Override
    public boolean isAccountNonExpired() { return true; }

    @Override
    public boolean isAccountNonLocked() { return true; }

    @Override
    public boolean isCredentialsNonExpired() { return true; }

    @Override
    public boolean isEnabled() { return user.getIsActive(); }
}
