package com.cocktail.security;

import com.cocktail.repository.CocktailRepository;
import com.cocktail.repository.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.approval.TokenApprovalStore;
import org.springframework.security.oauth2.provider.approval.TokenStoreUserApprovalHandler;
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestFactory;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
//            .antMatchers(HttpMethod.GET, "/cocktails/**").permitAll()
//            .antMatchers(HttpMethod.GET, "/drinks/**").permitAll()
            .antMatchers(HttpMethod.POST, "/cocktails/**").hasAuthority("ADMIN")
            .antMatchers(HttpMethod.PUT, "/cocktails/**").hasAuthority("ADMIN")
            .antMatchers(HttpMethod.PATCH, "/cocktails/**").hasAuthority("ADMIN")
            .antMatchers(HttpMethod.POST, "/drinks/**").hasAuthority("ADMIN")
            .antMatchers(HttpMethod.PUT, "/drinks/**").hasAuthority("ADMIN")
            .antMatchers(HttpMethod.PATCH, "/drinks/**").hasAuthority("ADMIN").and()
            .csrf().disable();
    }
//    @Bean
//
//    public CustomBasicAuthenticationEntryPoint getBasicAuthEntryPoint(){
//        return new CustomBasicAuthenticationEntryPoint();
//    }
//    /* To allow Pre-flight [OPTIONS] request from browser */
//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
//    }


}
