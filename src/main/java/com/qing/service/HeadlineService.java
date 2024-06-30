package com.qing.service;

import com.qing.pojo.Headline;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qing.pojo.PortalVo;
import com.qing.utils.Result;

/**
* @author Administrator
* @description 针对表【news_headline】的数据库操作Service
* @createDate 2024-06-30 04:27:41
*/
public interface HeadlineService extends IService<Headline> {

    Result findNewsPage(PortalVo portalVo);
}
