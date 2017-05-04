package com.cocktail.security;

import com.cocktail.repository.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@SuppressWarnings("WeakerAccess")
@Configuration
@EnableWebSecurity
public class OAuth2SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private static String REALM="MY_TEST_REALM";

    @Bean
    public UserDetailsService userDetailsServiceBean() {
        return new UserDetailServiceImpl();
    }

    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService( this.userDetailsServiceBean());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    http.httpBasic().and().anonymous().disable()
            .authorizeRequests()
            .antMatchers(HttpMethod.POST, "/cocktails/**").hasAuthority("ADMIN")
            .antMatchers(HttpMethod.PUT, "/cocktails/**").hasAuthority("ADMIN")
            .antMatchers(HttpMethod.PATCH, "/cocktails/**").hasAuthority("ADMIN")
            .antMatchers(HttpMethod.POST, "/users/**").hasAuthority("ADMIN")
            .antMatchers(HttpMethod.PUT, "/users/**").hasAuthority("ADMIN")
            .antMatchers(HttpMethod.PATCH, "/users/**").hasAuthority("ADMIN")
            .antMatchers(HttpMethod.POST, "/drinks/**").hasAuthority("ADMIN")
            .antMatchers(HttpMethod.PUT, "/drinks/**").hasAuthority("ADMIN")
            .antMatchers(HttpMethod.PATCH, "/drinks/**").hasAuthority("ADMIN").and()
            .csrf().disable();
    }
}
