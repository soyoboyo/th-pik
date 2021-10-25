package com.jza.service;

import com.fasterxml.jackson.databind.json.JsonMapper;
import com.jz.dto.UserGithubDetails;
import com.jz.dto.UserInfo;
import com.jz.repository.LoginSummaryRepository;
import com.jz.service.HttpGithubService;
import com.jz.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserServiceTest {

	private LoginSummaryRepository loginSummaryRepository;
	private HttpGithubService httpGithubService;
	private UserService userService;
	private final JsonMapper mapper = new JsonMapper();

	public UserServiceTest() {
		this.loginSummaryRepository = mock(LoginSummaryRepository.class);
		this.httpGithubService = mock(HttpGithubService.class);
		this.userService = new UserService(loginSummaryRepository, httpGithubService);
	}


	@Test
	@DisplayName("For successful response, return correct RepoInfo")
	void givenSampleGitbubResponseBuildUserInfo() throws IOException, ParseException {
		// given
		String login = "octocat";
		UserInfo expected = getExpectedResult();

		// when
		when(httpGithubService.fetchGithubDetailsFor(any())).thenReturn(getSampleResponseFromGithubApi());
		UserInfo actual = userService.getUserInfo(login);

		// then
		assertAll(() -> {
			Assertions.assertEquals(expected.getId(), actual.getId());
			Assertions.assertEquals(expected.getName(), actual.getName());
			Assertions.assertEquals(expected.getLogin(), actual.getLogin());
			Assertions.assertEquals(expected.getAvatarUrl(), actual.getAvatarUrl());
			Assertions.assertEquals(expected.getCalculations(), actual.getCalculations());
		});

	}

	private UserInfo getExpectedResult() throws ParseException {
		return UserInfo.builder()
				.id("583231")
				.name("The Octocat")
				.avatarUrl("https://avatars.githubusercontent.com/u/583231?v=4")
				.calculations(0.01466275659824047)
				.login("octocat")
				.build();

	}

	private UserGithubDetails getSampleResponseFromGithubApi() throws IOException {
		String response = "{\n" +
				"  \"login\": \"octocat\",\n" +
				"  \"id\": 583231,\n" +
				"  \"node_id\": \"MDQ6VXNlcjU4MzIzMQ==\",\n" +
				"  \"avatar_url\": \"https://avatars.githubusercontent.com/u/583231?v=4\",\n" +
				"  \"gravatar_id\": \"\",\n" +
				"  \"url\": \"https://api.github.com/users/octocat\",\n" +
				"  \"html_url\": \"https://github.com/octocat\",\n" +
				"  \"followers_url\": \"https://api.github.com/users/octocat/followers\",\n" +
				"  \"following_url\": \"https://api.github.com/users/octocat/following{/other_user}\",\n" +
				"  \"gists_url\": \"https://api.github.com/users/octocat/gists{/gist_id}\",\n" +
				"  \"starred_url\": \"https://api.github.com/users/octocat/starred{/owner}{/repo}\",\n" +
				"  \"subscriptions_url\": \"https://api.github.com/users/octocat/subscriptions\",\n" +
				"  \"organizations_url\": \"https://api.github.com/users/octocat/orgs\",\n" +
				"  \"repos_url\": \"https://api.github.com/users/octocat/repos\",\n" +
				"  \"events_url\": \"https://api.github.com/users/octocat/events{/privacy}\",\n" +
				"  \"received_events_url\": \"https://api.github.com/users/octocat/received_events\",\n" +
				"  \"type\": \"User\",\n" +
				"  \"site_admin\": false,\n" +
				"  \"name\": \"The Octocat\",\n" +
				"  \"company\": \"@github\",\n" +
				"  \"blog\": \"https://github.blog\",\n" +
				"  \"location\": \"San Francisco\",\n" +
				"  \"email\": null,\n" +
				"  \"hireable\": null,\n" +
				"  \"bio\": null,\n" +
				"  \"twitter_username\": null,\n" +
				"  \"public_repos\": 8,\n" +
				"  \"public_gists\": 8,\n" +
				"  \"followers\": 4092,\n" +
				"  \"following\": 9,\n" +
				"  \"created_at\": \"2011-01-25T18:44:36Z\",\n" +
				"  \"updated_at\": \"2021-10-22T14:27:39Z\"\n" +
				"}";
		return mapper.reader().readValue(response, UserGithubDetails.class);
	}
}
