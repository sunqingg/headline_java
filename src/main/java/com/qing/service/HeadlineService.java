package com.qing.service;

import com.qing.pojo.Headline;
import com.qing.pojo.HeadlineVo;
import com.qing.pojo.Type;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qing.utils.Result;

/**
* @author Administrator
* @description 针对表【news_type】的数据库操作Service
* @createDate 2024-06-30 04:27:41
*/
public interface HeadlineService extends IService<Type> {

    Result publish(HeadlineVo headline, int userId);

    Result findHeadlineByHid(String hid);

    Result update(Headline headline);

    Result removeByHid(Integer hid);
}
