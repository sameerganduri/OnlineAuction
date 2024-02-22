package com.onlineauction.oauth;

import java.util.Objects;

import javax.transaction.Transactional;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Service;

import com.onlineauction.entity.OauthClient;
import com.onlineauction.repository.OAuthClientRepository;


@Service
@EnableJpaRepositories
@Configuration
@Transactional
public class CustomClientDetailsService implements ClientDetailsService{

	private OAuthClientRepository clientRepository;
	
	public CustomClientDetailsService(OAuthClientRepository clientRepository)
	{
		this.clientRepository = clientRepository;
	}
	
	@Override
	@Cacheable("loadClientByClientId")
	public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
		

		OauthClient client = clientRepository.findByClientId(clientId);
		if(Objects.isNull(client)) {
			throw new ClientRegistrationException(clientId);
			
		}
		return new CustomClientDetails(client);
	}

}
