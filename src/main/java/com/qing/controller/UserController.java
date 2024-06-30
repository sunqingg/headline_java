package com.qing.controller;

import com.qing.pojo.User;
import com.qing.service.UserService;
import com.qing.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("login")
    public Result login(@RequestBody User user){
//        1.获取user_id
//        userService.userPass2Id(user);
        return  userService.getIdByUser(user);
    }
}
