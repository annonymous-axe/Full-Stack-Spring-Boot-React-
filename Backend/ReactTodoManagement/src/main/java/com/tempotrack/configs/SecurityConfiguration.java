package com.tempotrack.configs;

import com.tempotrack.services.CustomeUserDetailsService;
import com.tempotrack.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.sql.DataSource;

@Configuration
public class SecurityConfiguration {

	@Autowired
	JwtFilter jwtFilter;

	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception {
        
		return http
				.authorizeHttpRequests((authorize) -> 
							authorize
							.requestMatchers(HttpMethod.POST, "/loginUser").permitAll()
							.requestMatchers(HttpMethod.POST, "/saveUser").permitAll()
							.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
							.anyRequest().authenticated())
				.httpBasic(Customizer.withDefaults()) // for basic security
				// Stateless session management
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.csrf(csrf -> csrf.disable())
				.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
				.build();
	}

	@Bean
	public UserDetailsService userDetailsService(UserService userService){
		return new CustomeUserDetailsService(userService);
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationProvider authenticationProvider(CustomeUserDetailsService userDetailsService) {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setPasswordEncoder(passwordEncoder());
		authProvider.setUserDetailsService(userDetailsService);

		return authProvider;
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config)throws Exception{
		return config.getAuthenticationManager();
	}

}