package com.example.demo.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.demo.enuum.UserRole;
import com.example.demo.services.UserServices;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity

public class WebSecurityConfiguration {
	@Autowired
private JwtAuthenticationFilter jwtAuthenticationFilter;
	@Autowired
private UserServices userService;

@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
	http.csrf(AbstractHttpConfigurer::disable)
    .authorizeHttpRequests(request->
    request.dispatcherTypeMatchers(jakarta.servlet.DispatcherType.ASYNC).permitAll().requestMatchers("/api/auth/**").permitAll()
    .requestMatchers("/api/admin/**").hasAnyAuthority(UserRole.Admin.name())
    .requestMatchers("/api/user/**").hasAnyAuthority(UserRole.Client.name())
    .requestMatchers("/api/responsable/**").hasAnyAuthority(UserRole.Responsable.name())
    .requestMatchers("/ws/**").permitAll()
    .anyRequest().authenticated()).sessionManagement(manager 
    ->manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS)).authenticationProvider(authenticationProvider())
    .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    return http.build();
}
@Bean
public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
}


@Bean
public AuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider authProvider=new DaoAuthenticationProvider();
    authProvider.setUserDetailsService(userService.userDetailsService());
    authProvider.setPasswordEncoder(passwordEncoder());
    return authProvider;
}
@Bean
public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
    return config.getAuthenticationManager();
}

}
