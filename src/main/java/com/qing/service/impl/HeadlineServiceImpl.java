package com.qing.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qing.mapper.UserMapper;
import com.qing.pojo.Headline;
import com.qing.pojo.PortalVo;
import com.qing.service.HeadlineService;
import com.qing.mapper.HeadlineMapper;
import com.qing.utils.JwtHelper;
import com.qing.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @author Administrator
* @description 针对表【news_headline】的数据库操作Service实现
* @createDate 2024-06-30 04:27:41
*/
@Service
public class HeadlineServiceImpl extends ServiceImpl<HeadlineMapper, Headline>
    implements HeadlineService{
    @Autowired
    private HeadlineMapper headlineMapper;
    @Autowired
    JwtHelper jwtHelper;
    @Override
    public Result findNewsPage(PortalVo portalVo) {
        IPage<Headline> page = new Page<>(portalVo.getPageNum(),portalVo.getPageSize());
        headlineMapper.selectPage(page,null);
        List<Headline> records = page.getRecords();
        HashMap<Object, Object> map = new HashMap<>();
        map.put("pageData",page.getRecords());
        map.put("pageNum",page.getCurrent());
        map.put("pageSize",page.getSize());
        map.put("totalPage",page.getPages());
        map.put("totalSize",page.getTotal());

        HashMap<Object, Object> hashMap = new HashMap<>();
        hashMap.put("pageInfo",map);
        return Result.ok(hashMap);

    }
}




