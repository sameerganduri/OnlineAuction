package com.onlineauction.oauth;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.onlineauction.entity.Role;
import com.onlineauction.entity.User;

import lombok.AllArgsConstructor;


public class CustomUserDetails implements UserDetails{

	public CustomUserDetails(User users, List<Role> roles) {
		
		this.users = users;
		this.roles = roles;
	}

	private User users;
	private List<Role> roles;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		return roles.stream().map(role->{
			return new SimpleGrantedAuthority("ROLE".concat(role.getRoleName()));
			
		}).collect(Collectors.toList());
	}

	@Override
	public String getPassword() {
		
		return this.users.getPassword();
	}

	@Override
	public String getUsername() {
		
		return this.users.getUserName();
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
		
		return "Y".equals(this.users.getStatus());
	}
	
	public User getUsersDetails() {
		return this.users;
	}

}
