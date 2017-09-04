package com.ai.base.service.core;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ai.base.mapper.core.UserMapper;
import com.ai.base.model.core.User;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    /**
     * 通过手机号码获取用户表信息
     *
     * @param phoneNo
     * @return
     */
    @Transactional(readOnly = true)
    public List<User> getUserByPhoneNo(String phoneNo) {
        return userMapper.getUserByPhoneNo(phoneNo);
    }

    /**
     * 通过登陆工号获取用户表信息
     *
     * @param staffCode
     * @return
     */
    @Transactional(readOnly = true)
    public List<User> getUserByStaffCode(String staffCode) {
        return userMapper.getUserByStaffCode(staffCode);
    }

    /**
     * 根据用户ID获取用户信息
     *
     * @param staffId
     * @return
     */
    @Transactional(readOnly = true)
    public User getUserByStaffId(long staffId) {
        User user = userMapper.getUserByStaffId(staffId);
        return user;
    }

    /**
     * 根据Token获得用户信息
     *
     * @param loginToken
     * @return
     */
    @Transactional(readOnly = true)
    public User getUserByToken(String loginToken) {
        User user = userMapper.getUserByToken(loginToken);
        return user;
    }
}
