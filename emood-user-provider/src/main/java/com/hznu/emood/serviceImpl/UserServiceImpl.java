package com.hznu.emood.serviceImpl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.hznu.emood.mapper.UserMapper;
import com.hznu.emood.model.User;
import com.hznu.emood.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserByUsername(String username) {
        User userForBase = null;
        try {
            userForBase = userMapper.getUserByUsername(username);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return userForBase;
    }

    @Override
    public void saveUser(User user) {
        userMapper.insertUser(user);
    }

    @Override
    public User getUserById(String userId) {
        User user = null;
        try {
            user = userMapper.getUserById(userId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return user;
    }

    @Override
    public String getUsernameById(Long userId) {
        return userMapper.getUsernameById(userId);
    }

    @Override
    public Boolean getVipByUserId(Long userId) {
        return userMapper.getVipByUserId(userId);
    }

    @Override
    public void updateUserVip(String userId) {
        userMapper.updateUserVip(userId);
    }
}
