package com.example.jwt.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.jwt.annotation.JwtIgnore;
import com.example.jwt.common.response.Result;
import com.example.jwt.entity.Audience;
import com.example.jwt.module.system.entity.UserEntity;
import com.example.jwt.module.system.service.UserService;
import com.example.jwt.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@RestController
public class AdminUserController {

    @Resource
    private Audience audience;

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    @JwtIgnore
    public Result adminLogin(HttpServletResponse response, String username, String password) {
        String role = "admin";
        if(StringUtils.isNotEmpty(username) && StringUtils.isNotEmpty(password)){
            UserEntity userEntity = userService.selectByUsername(username);
            if(null == userEntity){
                return Result.FAIL("-------------用户不存在-------------");
            }else{
                if(password.equals(userEntity.getPassword())){
                    // 创建token
                    String userId = userEntity.getId();
                    String token = JwtUtil.createJWT(userId, username, role, audience);
                    log.info("### 登录成功, token={} ###", token);
                    // 将token放在响应头
                    response.setHeader(JwtUtil.AUTH_HEADER_KEY, JwtUtil.TOKEN_PREFIX + token);
                    // 将token响应给客户端
                    JSONObject result = new JSONObject();
                    result.put("token", token);
                    return Result.SUCCESS(result);
                }else{
                    return Result.FAIL("密码错误");
                }
            }
        }else{
            log.error("------------用户名或密码不能为空----------");
            return Result.FAIL("用户名或密码不能为空");
        }
    }

    @GetMapping("/users")
    public Result userList() {
        log.info("### 查询所有用户列表 ###");
        return Result.SUCCESS();
    }

}
