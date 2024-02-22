package com.onlineauction.config;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.util.StringUtils;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.filter.CorsFilter;

import com.onlineauction.oauth.CustomUserDetailsService;
@EnableWebSecurity
@Configuration
@EnableAuthorizationServer
public class WebSecurityConfig  extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private CustomUserDetailsService userDetailsService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// auth.user
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {

		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .authorizeRequests()
        .antMatchers("/","/v2/api-docs", "/configuration/**", "/swagger*/**", "/webjars/**").permitAll()
        .antMatchers("/api/**").hasAnyAuthority("ADMIN", "USER")
        .antMatchers("/api/**").hasAuthority("ADMIN")
        .antMatchers("/api/**").authenticated()
        .anyRequest().authenticated();
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
	}

	@Bean
	public TokenStore tokenStore() {
		return new InMemoryTokenStore();
	}

	@Bean
    public FilterRegistrationBean<Filter> corsFilter() {
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<Filter>();
        filterRegistrationBean.setFilter(new CorsFilter(request -> {
            String origin = request.getHeader(HttpHeaders.ORIGIN);
            if (!StringUtils.hasText(origin)) {
                return null;
            }
        
            CorsConfiguration configuration = new CorsConfiguration();
            configuration.addAllowedOrigin(origin);
            String accessControlRequestHeaders = request.getHeader(HttpHeaders.ACCESS_CONTROL_REQUEST_HEADERS);
            if (StringUtils.hasText(accessControlRequestHeaders)) {
                Stream.of(accessControlRequestHeaders.split(",")).map(String::trim).distinct()
                        .forEach(configuration::addAllowedHeader);
            }
            configuration.addExposedHeader("*");
            configuration.setAllowCredentials(true);
            List<String> list = new ArrayList<String>();
			list.add("GET");
			list.add("HEAD");
			list.add("POST");
			list.add("PUT");
			list.add("PATCH");
			list.add("DELETE");
			list.add("OPTIONS");
			list.add("TRACE");
            configuration
                    .setAllowedMethods(list);
            return configuration;
        }));
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.setOrder(Integer.MIN_VALUE);
        return filterRegistrationBean;
    }
}


