package com.qing.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qing.pojo.User;
import com.qing.service.UserService;
import com.qing.mapper.UserMapper;
import com.qing.utils.JwtHelper;
import com.qing.utils.MD5Util;
import com.qing.utils.Result;
import com.qing.utils.ResultCodeEnum;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @author Administrator
* @description 针对表【news_user】的数据库操作Service实现
* @createDate 2024-06-30 04:27:41
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{
    @Autowired
    private UserMapper userMapper;
    @Autowired
    JwtHelper jwtHelper;

    @Override
    public Result getIdByUser(User user) {
        if (user.getUsername() == null) {
            return Result.build(null,ResultCodeEnum.USERNAME_ERROR);
        }
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername,user.getUsername());
        User loginUser = userMapper.selectOne(wrapper);
        if (loginUser == null) {
            return Result.build("登录异常", ResultCodeEnum.USERNAME_ERROR);
        }

        if (!StringUtils.isEmpty(loginUser.getUserPwd()) && loginUser.getUserPwd().equals(MD5Util.encrypt(user.getUserPwd())) ){
            String token = jwtHelper.createToken(Long.valueOf(loginUser.getUid()));
            Map map = new HashMap<>();
            map.put("token",token);
            return Result.ok(map);
        }
        return Result.build(null,ResultCodeEnum.PASSWORD_ERROR);
    }

    @Override
    public Result getUserInfo(HttpServletRequest request) {
        String token = request.getHeader("token");
        if (jwtHelper.isExpiration(token)){
            return Result.build(null,ResultCodeEnum.NOTLOGIN);
        }
        Long userId = jwtHelper.getUserId(token);
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUid,userId);
        User user = userMapper.selectOne(wrapper);

        if (user != null) {
            user.setUserPwd(null);
            Map<String, Object> map = new HashMap<>();
            map.put("loginUser",user);
            return Result.ok(map);
        }else {
            return Result.build(null,ResultCodeEnum.NOTLOGIN);
        }

    }

    @Override
    public Result checkUserName(String username) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername,username);
        List<User> userList = userMapper.selectList(wrapper);
        if (userList.size() > 0){
            return Result.build(null,ResultCodeEnum.USERNAME_USED);
        }else {
            return Result.ok(null);
        }
    }

    @Override
    public Result regist(User user) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername,user.getUsername());
        List<User> userList = userMapper.selectList(wrapper);
        if (userList.size() == 0) {
            String encrypt = MD5Util.encrypt(user.getUserPwd());
//        加密密码
            user.setUserPwd(encrypt);
            int insertRows = userMapper.insert(user);
            if (insertRows == 1){
                return Result.ok(null);
            }else {
                return Result.build(null,ResultCodeEnum.USERNAME_ERROR);
            }
        }else {
            return Result.build(null,ResultCodeEnum.USERNAME_USED);
        }

    }

    @Override
    public Result checkLogin(String token) {
        if (jwtHelper.isExpiration(token)){
            return Result.build(null,ResultCodeEnum.NOTLOGIN);
        }
        Long userId = jwtHelper.getUserId(token);
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUid,userId);
        List<User> list = userMapper.selectList(wrapper);
        if (list.size() >= 1){
            return Result.ok(null);
        }else {
            return Result.build(null,ResultCodeEnum.USERNAME_ERROR);
        }
    }
}




