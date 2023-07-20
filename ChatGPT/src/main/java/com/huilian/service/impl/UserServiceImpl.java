package com.huilian.service.impl;

import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.huilian.entity.User;
import com.huilian.mapper.AdminDao;
import com.huilian.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;


/**
 *用户接口实现类
 */
@Service
public class UserServiceImpl implements UserService {
	//注入userMapper
	@Autowired
	private AdminDao userMapper;
	//通过User的用户账号和用户密码查询用户信息
	@Override
	public User login(User user) {
		List<User> users = userMapper.selectList(new QueryWrapper<User>()
				.eq("no", user.getNo())
				.eq("password", user.getPassword()));
		if(CollectionUtils.isEmpty(users)){
			return null;
		}
		return users.get(0);
	}
	@Override
	public User findUserAll(User user) {
		// TODO Auto-generated method stub
		List<User> no = userMapper.selectList(new QueryWrapper<User>()
				.eq("no", user.getNo()));
		if(CollectionUtils.isEmpty(no)){
			return null;
		}
		return no.get(0);
	}

	@Override
	public void save(User user) {
		userMapper.insert(user);

	}

	//查询全部用户
	
}
