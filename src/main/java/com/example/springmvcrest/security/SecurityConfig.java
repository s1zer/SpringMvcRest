package com.example.springmvcrest.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        return passwordEncoder;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/", "/api", "/css/main.css").permitAll()
                .antMatchers("/panel/admin/**", "/api/panel/admin").hasAuthority("ROLE_ADMIN")
                .antMatchers("/panel/user", "api/panel/user").access("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
                .antMatchers("/register").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .and()
                .exceptionHandling().accessDeniedPage("/accessDenied")
                .and()
                .logout()
                .logoutUrl("/logout")
                .and()
                .logout()
                .logoutSuccessUrl("/");
    }
}