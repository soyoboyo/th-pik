package com.jz.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Builder
@Getter

public class UserInfo {

	private String id;
	private String login;
	private String name;
	private String type;
	private String avatarUrl;
	private Date createdAt;
	private Double calculations;


	public static UserInfo buildUserInfoWith(UserGithubDetails details) {
		return UserInfo.builder()
				.id(details.getId())
				.login(details.getLogin())
				.name(details.getName())
				.avatarUrl(details.getAvatarUrl())
				.createdAt(details.getCreatedAt())
				.calculations(details.getCalculations())
				.build();
	}

}
