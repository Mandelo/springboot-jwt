package com.example.jwt.module.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.jwt.module.system.entity.UserEntity;

public interface UserService extends IService<UserEntity>{

    UserEntity selectByUsername(String username);

}
