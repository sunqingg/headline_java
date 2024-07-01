package com.qing.controller;

import com.alibaba.druid.util.StringUtils;
import com.qing.pojo.User;
import com.qing.service.UserService;
import com.qing.utils.Result;
import com.qing.utils.ResultCodeEnum;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
@CrossOrigin
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("login")
    public Result login(@RequestBody User user){
//        1.获取user_id
//        userService.userPass2Id(user);
        return  userService.getIdByUser(user);
    }

    @RequestMapping("getUserInfo")
    public Result getUserInfo(HttpServletRequest request){
        return userService.getUserInfo(request);
    }

    @RequestMapping("checkUserName")
    public Result checkUserName(String username){
        return userService.checkUserName(username);
    }

    @RequestMapping("regist")
    public Result regist(@RequestBody User user){
        return userService.regist(user);
    }

    @RequestMapping("checkLogin")
    public Result checkLogin(HttpServletRequest request){
        String token = request.getHeader("token");
        log.warn("token:------------------->" + token);
        if (StringUtils.isEmpty(token)) {
            return Result.build(null, ResultCodeEnum.NOTLOGIN);
        }
        return userService.checkLogin(token);
    }
}
