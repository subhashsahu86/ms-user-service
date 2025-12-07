package com.subh.msUserServiceApp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.RequestMatchers;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	private final UserDetailsService userDetailsService;

	//For inmemory authentication
	/*
	 * @Bean public UserDetailsService userDetailsService() { UserDetails user =
	 * User.withUsername("admin") .password("{noop}admin123") // plain password
	 * .roles("USER") .build();
	 * 
	 * return new InMemoryUserDetailsManager(user); 
	 * }
	 */
	 

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
        .csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(auth -> auth
        		.requestMatchers("/api/v1/users/register", "/home").permitAll()
        		//.requestMatchers("/register", "/login").denyAll()
        		.anyRequest().authenticated())
        
        .formLogin(form -> form
        		.loginPage("/login")
        		.defaultSuccessUrl("/home")	
        		.failureUrl("/login?error=true")
        		.permitAll()
        		
        		).logout(logout -> logout
        				.logoutUrl("/logout")
        				.logoutSuccessUrl("/login?logout=true")
        				);
        
        return http.build();
    }
    
    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
                     AuthenticationManagerBuilder builder =
                    		 http.getSharedObject(AuthenticationManagerBuilder.class);
                     
                     builder
                     .userDetailsService(userDetailsService)
                     .passwordEncoder(passwordEncoder());
                     
                     return builder.build();
    }
}

