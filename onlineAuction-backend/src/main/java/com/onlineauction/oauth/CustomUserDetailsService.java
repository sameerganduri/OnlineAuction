package com.onlineauction.oauth;

import java.util.List;
import java.util.Objects;

import javax.transaction.Transactional;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.onlineauction.entity.Role;
import com.onlineauction.entity.User;
import com.onlineauction.repository.RoleRepository;
import com.onlineauction.repository.UserRepository;

@Service(value="userService")
@Configuration
@Transactional
public class CustomUserDetailsService implements UserDetailsService {

	private UserRepository userRepository;
	
	private RoleRepository roleRepository;

	public CustomUserDetailsService(UserRepository userRepository, RoleRepository roleRepository) {
		super();
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
	}
	
	

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

		System.out.println("username"+userName);
		BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
		System.out.println("bcrypt "+encoder.encode("password123"));
		User users = this.userRepository.findByUserName(userName);
		if(Objects.isNull(users)) {
			throw new UsernameNotFoundException(userName);
			
		}
		System.out.println("user object "+users.toString());
		List<Role> roles = this.roleRepository.findAllByRoleId(Integer.parseInt(users.getRoles()));
		return new CustomUserDetails(users,roles);
	}
	
}
