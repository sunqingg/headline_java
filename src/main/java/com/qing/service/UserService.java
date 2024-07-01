package com.qing.service;

import com.qing.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qing.utils.Result;
import jakarta.servlet.http.HttpServletRequest;

/**
* @author Administrator
* @description 针对表【news_user】的数据库操作Service
* @createDate 2024-06-30 04:27:41
*/
public interface UserService extends IService<User> {
    Result getIdByUser(User user);

    Result getUserInfo(HttpServletRequest request);

    Result checkUserName(String username);

    Result regist(User user);

    Result checkLogin(String token);
}
