package com.huilian.service;

import com.huilian.entity.User;

import java.util.List;


/**
 *用户接口
 */
public interface UserService {
	//通过User的用户账号和用户密码查询用户信息
    User login(User user);
    
    //查询全部用户
    User findUserAll(User user);

    void save(User user);
}
