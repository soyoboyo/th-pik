package com.jz.controller;

import com.jz.api.UserApi;
import com.jz.dto.UserInfo;
import com.jz.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController implements UserApi {

	private final UserService userService;

	@Override
	public ResponseEntity<UserInfo> getStats(@PathVariable String login) {
		UserInfo info = userService.getUserInfo(login);
		return ResponseEntity.ok(info);
	}
}
