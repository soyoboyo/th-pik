package com.jz.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;

@Repository
@RequiredArgsConstructor
public class LoginSummaryRepository {

	private final RedisTemplate<String, Object> redisTemplate;
	private ValueOperations<String, Object> valueOperations;

	@PostConstruct
	public void postConstruct() {
		this.valueOperations = redisTemplate.opsForValue();
	}

	public Long incrementCountFor(String login) {
		return valueOperations.increment(login);
	}
}
