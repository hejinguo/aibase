package com.ai.base.service.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ai.base.mapper.core.SmsMapper;

/**
 * 短信
 * @author hejg
 *
 */
@Service
public class SmsService {
	@Autowired
	private SmsMapper smsMapper;
	
	/**
	 * 发送登陆验证码短信
	 * @param phoneNo
	 * @param loginMark
	 */
	@Transactional(readOnly=true)
	public void sendLoginMark(String phoneNo,String loginMark){
		smsMapper.sendLoginMark(phoneNo, loginMark);
	}
	
	/**
	 * 发送通用性质短信
	 * @param phoneNo
	 * @param content
	 */
	@Transactional(readOnly=true)
	public void sendCommonMsg(String phoneNo,String content){
		smsMapper.sendCommonMsg(phoneNo, content);
	}
}
