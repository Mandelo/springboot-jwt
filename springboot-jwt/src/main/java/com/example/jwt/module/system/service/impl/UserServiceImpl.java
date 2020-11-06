package com.example.jwt.module.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.jwt.module.system.dao.UserMapper;
import com.example.jwt.module.system.entity.UserEntity;
import com.example.jwt.module.system.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper,UserEntity> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserEntity selectByUsername(String username) {
        LambdaQueryWrapper<UserEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserEntity::getUsername,username);
        return userMapper.selectOne(queryWrapper);
    }

}
