package com.ai.base.mapper.core;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ai.base.model.core.User;

/**
 * 用户管理
 * @author hejg
 */
@Repository
public interface UserMapper {

    /**
     * 通过手机号码获取用户表信息
     *
     * @param phoneNo
     * @return
     */
    List<User> getUserByPhoneNo(String phoneNo);

    /**
     * 通过登陆工号获取用户表信息
     *
     * @param staffCode
     * @return
     */
    List<User> getUserByStaffCode(String staffCode);

    /**
     * 根据用户ID获取用户信息
     *
     * @param staffId
     * @return
     */
    User getUserByStaffId(long staffId);

    /**
     * 根据Token获得用户信息
     *
     * @param loginToken
     * @return
     */
    User getUserByToken(String loginToken);
}
