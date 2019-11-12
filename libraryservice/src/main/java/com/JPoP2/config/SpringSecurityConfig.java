package com.JPoP2.config;

import com.JPoP2.error.InvalidPassword;
import com.JPoP2.error.UserNotFoundException;
import org.springframework.security.core.userdetails.User;

import com.JPoP2.model.enums.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
	@Value("$(api.version}")
	String apiVersion;
	
	@Autowired
	private InMemoryUserDetailsManager manager;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(manager);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.httpBasic().and().authorizeRequests()
				.antMatchers(HttpMethod.GET, "/api/" + apiVersion + "/lib/books").hasAnyRole(Role.USER.toString(), Role.ADMIN.toString())
				.antMatchers(HttpMethod.GET, "/api/" + apiVersion + "/lib/books/**").hasAnyRole(Role.USER.toString(), Role.ADMIN.toString())
				.antMatchers(HttpMethod.POST, "/api/" + apiVersion + "/lib/books").hasRole(Role.ADMIN.toString())
				.antMatchers(HttpMethod.DELETE, "/api/" + apiVersion + "/lib/books/**").hasRole(Role.ADMIN.toString())
				.antMatchers(HttpMethod.GET, "/api/" + apiVersion + "/lib/users").hasAnyRole(Role.USER.toString(), Role.ADMIN.toString())
				.antMatchers(HttpMethod.GET, "/api/" + apiVersion + "/lib/users/**").hasAnyRole(Role.USER.toString(), Role.ADMIN.toString())
				.antMatchers(HttpMethod.DELETE, "/api/" + apiVersion + "/lib/users/**").hasRole(Role.ADMIN.toString())
				.antMatchers(HttpMethod.PUT, "/api/" + apiVersion + "/lib/users/**").hasAnyRole(Role.USER.toString(), Role.ADMIN.toString())
				.antMatchers(HttpMethod.PUT, "/api/" + apiVersion + "/lib/users/*/books/*").hasAnyRole(Role.USER.toString(), Role.ADMIN.toString())
				.and().csrf().disable().formLogin().disable();
	}

	public void addUserForAuthentication(String emailId, Role role) {
		User.UserBuilder users = User.builder();

		if (!manager.userExists(emailId))
			manager.createUser(users.username(emailId).password(passwordEncoder.encode("password")).roles(role.toString()).build());
	}
	
	public void deleteUserForAuthentication(String emailId) {
		if (manager.userExists(emailId))
			manager.deleteUser(emailId);
	}

	public String validateLogin(String emailId, String password) {
		if(!manager.userExists(emailId))
			throw new UserNotFoundException("User with id : " + emailId + " does not exists");

		if(!passwordEncoder.matches(password, manager.loadUserByUsername(emailId).getPassword()))
			throw new InvalidPassword("Invalid password for user with id : " + emailId);

		String auth = emailId + ":" + password;
        byte[] encodedAuth = Base64.getEncoder().encode(auth.getBytes(StandardCharsets.US_ASCII));

		return "Basic " + new String(encodedAuth);
	}
}