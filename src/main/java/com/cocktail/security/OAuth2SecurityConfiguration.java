package com.cocktail.security;

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

//	@Autowired
//	private ClientDetailsService clientDetailsService;
//
	@Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
        .withUser("Jorge").password("123").roles("ADMIN").and()
        .withUser("Ezequiel").password("123").roles("USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    http.httpBasic().and()
            .authorizeRequests()
            .antMatchers(HttpMethod.GET, "/cocktails/**").hasRole("ADMIN")
            .antMatchers(HttpMethod.POST, "/cocktails/**").hasRole("ADMIN")
            .antMatchers(HttpMethod.PUT, "/cocktails/**").hasRole("ADMIN")
            .antMatchers(HttpMethod.PATCH, "/cocktails/**").hasRole("ADMIN").and()
            .csrf().disable();
        }

    /* To allow Pre-flight [OPTIONS] request from browser */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
    }

//    @Bean
//    FilterRegistrationBean corsFilter() {
//        return new FilterRegistrationBean(new Filter() {
//            public void doFilter(ServletRequest req, ServletResponse res,
//                                 FilterChain chain) throws IOException, ServletException {
//                HttpServletRequest request = (HttpServletRequest) req;
//                HttpServletResponse response = (HttpServletResponse) res;
//                String method = request.getMethod();
//                // this origin value could just as easily have come from a database
//                response.setHeader("Access-Control-Allow-Origin", "*");
//                response.setHeader("Access-Control-Allow-Methods",
//                        "POST, GET, PUT, OPTIONS, DELETE");
//                response.setHeader("Access-Control-Max-Age", Long.toString(60 * 60));
//                response.setHeader("Access-Control-Allow-Credentials", "true");
//                response.setHeader("Access-Control-Allow-Headers", "X-Requested-With, Content-Type, Authorization, Origin, Accept, Access-Control-Request-Method, Access-Control-Request-Headers");
//                if ("OPTIONS".equals(method)) {
//                    response.setStatus(HttpStatus.OK.value());
//                }
//                else {
//                    chain.doFilter(req, res);
//                }
//            }
//
//            public void init(FilterConfig filterConfig) {
//            }
//
//            public void destroy() {
//            }
//        });
//    }
//    @Override
//    @Bean
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }
//
//
//	@Bean
//	public TokenStore tokenStore() {
//		return new InMemoryTokenStore();
//	}
//
//	@Bean
//	@Autowired
//	public TokenStoreUserApprovalHandler userApprovalHandler(TokenStore tokenStore){
//		TokenStoreUserApprovalHandler handler = new TokenStoreUserApprovalHandler();
//		handler.setTokenStore(tokenStore);
//		handler.setRequestFactory(new DefaultOAuth2RequestFactory(clientDetailsService));
//		handler.setClientDetailsService(clientDetailsService);
//		return handler;
//	}
//
//	@Bean
//	@Autowired
//	public ApprovalStore approvalStore(TokenStore tokenStore) throws Exception {
//		TokenApprovalStore store = new TokenApprovalStore();
//		store.setTokenStore(tokenStore);
//		return store;
//	}

}
