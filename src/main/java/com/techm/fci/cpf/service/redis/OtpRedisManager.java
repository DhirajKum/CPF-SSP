package com.techm.fci.cpf.service.redis;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;


@Component
public class OtpRedisManager {
	
	@Autowired RedisTemplate<String, String> redisTemplate;
	
	/**
	 * Add Otp Data to Redis
	 * @param mobile
	 * @param otp
	 * @param ttl
	 * @param timeUnit
	 */
	public void addOtpData(String mobile, String otp, Long ttl, TimeUnit timeUnit){
		redisTemplate.opsForHash().put("otp", mobile,otp);
		redisTemplate.expire(mobile, ttl, timeUnit);
	}
	
	/**
	 * Get otp data from redis
	 * @param mobile
	 */
	public String getOtpData(String mobile){
		return (String)redisTemplate.opsForHash().get("otp", mobile);
	}
	
	/**
	 * Get otp data from redis
	 * @param mobile
	 */
	public void deleteOtpData(String mobile){
        redisTemplate.opsForHash().delete("otp",mobile);
	}
	
	/**
	 * Get Otp Expire Time
	 * @param mobile
	 */
	public Long getOtpExpireTime(String mobile){
		return redisTemplate.getExpire(mobile, TimeUnit.MINUTES).longValue();
	}
	
		
}
