package com.booking.hotel.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.booking.hotel.filter.JwtFilter;
import com.booking.hotel.service.CustomUserDetailsService;

@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	 private static final String[] AUTH_LIST = {
		      "/v2/api-docs",
		      "/configuration/ui",
		      "/swagger-resources/**",
		      "/configuration/security",
		      "/swagger-ui.html",
		      "/webjars/**",
		      "/login"
		    };

	 
	@Autowired
	private CustomUserDetailsService userDetailsService;
	@Autowired
	private JwtFilter jwtFilter;
	

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http//
				.csrf()//
				.disable()//
				.authorizeRequests()//
				.antMatchers(AUTH_LIST)//
				.permitAll()//
				.anyRequest()//
				.authenticated()//
				.and()//
				.sessionManagement()//
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
	}

	@Bean(name = BeanIds.AUTHENTICATION_MANAGER)
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
}
