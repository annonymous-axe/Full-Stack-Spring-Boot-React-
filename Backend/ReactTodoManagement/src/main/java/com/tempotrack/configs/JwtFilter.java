package com.tempotrack.configs;

import java.io.IOException;

import com.tempotrack.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.tempotrack.services.JwtService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;

@Slf4j
@Component
public class JwtFilter extends OncePerRequestFilter {

	private final JwtService jwtService;

	private final ApplicationContext context;

	private final UserService userService;

	public JwtFilter(JwtService jwtService, ApplicationContext applicationContext, UserService userService){
		this.jwtService = jwtService;
		context = applicationContext;
		this.userService = userService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		log.info("Entering doFilterInternal.");

		String authHeader = request.getHeader("Authorization");
		
		String token = null;
		String username = null;

		if(authHeader != null && authHeader.startsWith("Bearer")) {
			token = authHeader.substring(7);
			username = jwtService.extractUsername(token);

			request.getSession().setAttribute("username", username);
		}

		if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails userDetails = context.getBean(SecurityConfiguration.class).userDetailsService(userService).loadUserByUsername(username);
			if(jwtService.validateToken(token, userDetails)) {
				UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				authToken.setDetails(new WebAuthenticationDetailsSource()
						.buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authToken);
			}
		}

		filterChain.doFilter(request, response);

		log.info("Exiting doFilterInternal.");
	}

}