package com.jz.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.Date;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserGithubDetails {

	@JsonProperty("id")
	private String id;

	@JsonProperty("login")
	private String login;

	@JsonProperty("name")
	private String name;

	@JsonProperty("type")
	private String type;

	@JsonProperty("avatar_url")
	private String avatarUrl;

	@JsonProperty("created_at")
	private Date createdAt;

	@JsonProperty("followers")
	private Integer followers;

	@JsonProperty("public_repos")
	private Integer publicRepos;


	public Double getCalculations() {
		return 6.0 / followers * (2.0 + publicRepos);
	}

}
