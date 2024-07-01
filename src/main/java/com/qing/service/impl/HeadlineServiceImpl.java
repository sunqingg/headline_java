package com.qing.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qing.mapper.HeadlineMapper;
import com.qing.pojo.Headline;
import com.qing.pojo.HeadlineVo;
import com.qing.pojo.Type;
import com.qing.service.HeadlineService;
import com.qing.mapper.TypeMapper;
import com.qing.utils.JwtHelper;
import com.qing.utils.Result;
import com.qing.utils.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;

/**
* @author Administrator
* @description 针对表【news_type】的数据库操作Service实现
* @createDate 2024-06-30 04:27:41
*/
@Service
public class HeadlineServiceImpl extends ServiceImpl<TypeMapper, Type>
    implements HeadlineService {

    @Autowired
    private HeadlineMapper headlineMapper;

    @Autowired
    private JwtHelper jwtHelper;
    @Override
    public Result publish(HeadlineVo headline, int userId) {


        ObjectMapper mapper = new ObjectMapper();
        Headline readValue = null;
        try {
            readValue = mapper.readValue(mapper.writeValueAsString(headline), Headline.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        readValue.setPublisher(userId);
        readValue.setCreateTime(new Date());
        readValue.setUpdateTime(new Date());
        readValue.setPageViews(0);
        System.out.println("readValue的输出: ----->" + readValue);
        int insert = headlineMapper.insert(readValue);
        if (insert > 0 ) {
            return Result.ok(null);
        }else {
            return Result.build(null,ResultCodeEnum.USERNAME_USED);
        }

//        headline.setCreateTime(new Date());
//        headline.setUpdateTime(new Date());
//        headline.setPageViews(0);
//        headlineMapper.insert(headline);
//        return Result.ok(null);

    }

    @Override
    public Result findHeadlineByHid(String hid) {
        LambdaQueryWrapper<Headline> objectLambdaQueryWrapper = new LambdaQueryWrapper<>();
        objectLambdaQueryWrapper.eq(Headline::getHid,hid);
        Headline headline = headlineMapper.selectOne(objectLambdaQueryWrapper);

        HashMap<String, Object> map = new HashMap<>();
        map.put("headline",headline);
        return Result.ok(map);
    }

    @Override
    public Result update(Headline headline) {
        headline.setUpdateTime(new Date());
        headlineMapper.updateById(headline);
        return Result.ok(null);
    }

    @Override
    public Result removeByHid(Integer hid) {
        headlineMapper.deleteById(hid);
        return Result.ok(null);
    }
}




