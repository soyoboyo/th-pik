package com.jz.service;

import com.jz.dto.UserGithubDetails;
import com.jz.dto.UserInfo;
import com.jz.repository.LoginSummaryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

	private final LoginSummaryRepository loginSummaryRepository;
	private final HttpGithubService httpGithubService;

	public UserInfo getUserInfo(@PathVariable String login) {
		Long currentCount = loginSummaryRepository.incrementCountFor(login);
		log.info("Current count for " + login + " is: " + currentCount);

		UserGithubDetails githubDetails = httpGithubService.fetchGithubDetailsFor(login);

		return UserInfo.buildUserInfoWith(githubDetails);
	}

}
