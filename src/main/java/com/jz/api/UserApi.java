package com.jz.api;


import com.jz.dto.UserInfo;
import com.jz.exception.ApiError;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/users")
public interface UserApi {

	@ApiOperation(
			value = "Get user details for user's login."
	)
	@ApiResponses({
			@ApiResponse(code = 200, message = "Details fetched successfully.", response = UserInfo.class),
			@ApiResponse(code = 404, message = "User not found.", response = ApiError.class)
	})
	@GetMapping("/{login}")
	ResponseEntity<UserInfo> getStats(@PathVariable String login);
}
