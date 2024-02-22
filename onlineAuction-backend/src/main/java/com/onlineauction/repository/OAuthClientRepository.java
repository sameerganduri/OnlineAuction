package com.onlineauction.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.onlineauction.entity.OauthClient;

@Repository
public interface OAuthClientRepository extends JpaRepository<OauthClient, Integer>{

	OauthClient findByClientId(String clientId);
}
