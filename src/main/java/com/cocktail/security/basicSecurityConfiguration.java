package com.cocktail.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
public class BasicSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Bean
    public UserDetailsService userDetailsServiceBean() {
        return new UserDetailServiceImpl();
    }

    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("Jorge").password("123").authorities("ADMIN");
        auth.userDetailsService( this.userDetailsServiceBean());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    http.httpBasic().and().anonymous().disable()
            .authorizeRequests()
            .anyRequest().authenticated().and()
            .csrf().disable();
    }
}
