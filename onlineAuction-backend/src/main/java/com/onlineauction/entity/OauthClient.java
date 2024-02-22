package com.onlineauction.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Table(name="oauth_clients")
@Entity
@Data
public class OauthClient {
	

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "client_id", nullable = false)
    private String clientId;

    @Column(name = "client_secret", nullable = false)
    private String clientSecret;

    @Column(name = "authorized_grant_types", nullable = false)
    private String authorizedGrantTypes;

    @Column(name = "scopes", nullable = false)
    private String scopes;

    @Column(name="scoped")
    private int scoped;
    
    @Column(name = "access_token_validity_seconds", nullable = false)
    private Integer accessTokenValiditySeconds;

    @Column(name = "refresh_token_validity_seconds", nullable = false)
    private Integer refreshTokenValiditySeconds;

    @Column(name = "registered_redirect_uri")
    private String registeredRedirectUri;

    @Column(name = "resource_ids")
    private String resourceIds;

    @Column(name = "auto_approve")
    private Integer autoApprove;

    @Column(name = "secret_required", nullable = false)
    private Integer secretRequired;

}
