package com.onlineauction.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

import com.onlineauction.oauth.CustomClientDetailsService;
import com.onlineauction.oauth.CustomUserDetailsService;


@EnableAuthorizationServer
@Configuration
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {


@Autowired
private CustomClientDetailsService clientDetailsService;

@Autowired
private AuthenticationManager authenticationManager;

@Autowired
private CustomUserDetailsService userDetailsService;

@Autowired
private TokenStore tokenStore;


@Override
public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
	//super.configure(security);
	security.checkTokenAccess("isAuthenticated()");
}

@Override
public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
	clients.withClientDetails(clientDetailsService);
}

@Override
public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
	endpoints.tokenStore(tokenStore)
	.authenticationManager(authenticationManager).userDetailsService(userDetailsService);
}







}
