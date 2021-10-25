package com.jz.service;

import com.jz.dto.UserGithubDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class HttpGithubService {

	private final RestTemplate httpClient = new RestTemplate();
	private final static String API_URL_GITHUB_USERS = "https://api.github.com/users/";

	public UserGithubDetails fetchGithubDetailsFor(String login) {
		String url = API_URL_GITHUB_USERS + login;
		return httpClient.getForObject(url, UserGithubDetails.class);
	}

}
