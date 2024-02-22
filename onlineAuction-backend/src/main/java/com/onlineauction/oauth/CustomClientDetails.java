package com.onlineauction.oauth;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;

import com.onlineauction.entity.OauthClient;

public class CustomClientDetails implements ClientDetails {

	
	private OauthClient  client;
	
	CustomClientDetails(OauthClient client) {
		this.client=client; 
	}
	
	@Override
	public String getClientId() {
		
		return this.client.getClientId();
	}

	@Override
	public Set<String> getResourceIds() {
		if (Objects.nonNull(this.client.getResourceIds()) && !this.client.getResourceIds().isEmpty()) {
			System.out.println("client resource IDs : " + client.getResourceIds());
			if(this.client.getResourceIds().contains(","))
				return new HashSet<>(Arrays.asList(this.client.getResourceIds().split(",")));
			else {
				Set<String> resourceIds = new HashSet<>();
				resourceIds.add(this.client.getResourceIds());
				return resourceIds;
			}
		}
		return Collections.emptySet();
	}

	@Override
	public boolean isSecretRequired() {

		return this.client.getSecretRequired() == 1;
	}

	@Override
	public String getClientSecret() {

		return this.client.getClientSecret();
	}

	@Override
	public boolean isScoped() {

		return this.client.getScoped() == 1;
	}

	@Override
	public Set<String> getScope() {
		if (Objects.nonNull(this.client.getScopes()) && !this.client.getScopes().isEmpty()) {
			return new HashSet<>(Arrays.asList(this.client.getScopes().split(",")));
		}
		return Collections.emptySet();
	}

	@Override
	public Set<String> getAuthorizedGrantTypes() {
		if (Objects.nonNull(this.client.getAuthorizedGrantTypes()) && !this.client.getAuthorizedGrantTypes().isEmpty()) {
			return new HashSet<>(Arrays.asList(this.client.getAuthorizedGrantTypes().split(",")));
		}
		return Collections.emptySet();
	}

	@Override
	public Set<String> getRegisteredRedirectUri() {
		if (Objects.nonNull(this.client.getRegisteredRedirectUri()) && !this.client.getRegisteredRedirectUri().isEmpty()) {
			return Arrays.stream(this.client.getRegisteredRedirectUri().split(","))
					.collect(Collectors.toSet());
		}
		return Collections.emptySet();
	}

	@Override
	public Collection<GrantedAuthority> getAuthorities() {

		return Collections.emptySet();
	}

	@Override
	public Integer getAccessTokenValiditySeconds() {

		return this.client.getAccessTokenValiditySeconds();
	}

	@Override
	public Integer getRefreshTokenValiditySeconds() {

		return this.client.getRefreshTokenValiditySeconds();
	}

	@Override
	public boolean isAutoApprove(String scope) {

		return this.client.getAutoApprove() == 1;
	}

	@Override
	public Map<String, Object> getAdditionalInformation() {

		return Collections.emptyMap();
	}
}
