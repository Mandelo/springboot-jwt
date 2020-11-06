package com.example.jwt.module.system.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.jwt.module.system.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<UserEntity> {
}
