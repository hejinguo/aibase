package com.ai.base.mapper.core;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 短信
 * @author hejg
 *
 */
@Repository
public interface SmsMapper {
	
	/**
	 * 发送登陆验证码短信
	 * @param phoneNo
	 * @param loginMark
	 */
	void sendLoginMark(@Param("phoneNo")String phoneNo,@Param("loginMark")String loginMark);
	
	/**
	 * 发送通用性质短信
	 * @param phoneNo
	 * @param content
	 */
	void sendCommonMsg(@Param("phoneNo")String phoneNo,@Param("content")String content);
}
