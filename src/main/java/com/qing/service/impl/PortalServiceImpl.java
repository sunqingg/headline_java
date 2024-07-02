package com.qing.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qing.mapper.TypeMapper;
import com.qing.pojo.Headline;
import com.qing.pojo.PortalVo;
import com.qing.pojo.Type;
import com.qing.service.PortalService;
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
public class PortalServiceImpl extends ServiceImpl<HeadlineMapper, Headline>
    implements PortalService {
    @Autowired
    private HeadlineMapper headlineMapper;

    @Autowired
    private TypeMapper typeMapper;

    @Autowired
    JwtHelper jwtHelper;
//    @Override
//    public Result findNewsPage(PortalVo portalVo) {
//        IPage<Headline> page = new Page<>(portalVo.getPageNum(),portalVo.getPageSize());
//        headlineMapper.selectPage(page,null);
//        List<Headline> records = page.getRecords();
//        HashMap<Object, Object> map = new HashMap<>();
//        map.put("pageData",page.getRecords());
//        map.put("pageNum",page.getCurrent());
//        map.put("pageSize",page.getSize());
//        map.put("totalPage",page.getPages());
//        map.put("totalSize",page.getTotal());
//
//        HashMap<Object, Object> hashMap = new HashMap<>();
//        hashMap.put("pageInfo",map);
//        return Result.ok(hashMap);
//
//    }

    @Override
    public Result findAllTypes() {
        List<Type> typeList = typeMapper.selectList(null);
        return Result.ok(typeList);
    }
    @Override
    public Result findNewsPage(PortalVo portalVo) {
        IPage<Headline> page = new Page<>(portalVo.getPageNum(),portalVo.getPageSize());
        LambdaQueryWrapper<Headline> queryWrapper = new LambdaQueryWrapper<>();
//        queryWrapper.like(Headline::getArticle,portalVo.getKeyWords()).or().
//                like(Headline::getTitle,portalVo.getKeyWords());
//        queryWrapper.like(!StringUtils.isEmpty(portalVo.getKeyWords()),Headline::getTitle,portalVo.getKeyWords())
//                        .eq(portalVo.getType() != null,Headline::getType,portalVo.getType());
        queryWrapper.like(!StringUtils.isEmpty(portalVo.getKeyWords()),Headline::getTitle,portalVo.getKeyWords());
        headlineMapper.selectPage(page,queryWrapper);
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

    @Override
    public Result showHeadlineDetail(Integer hid) {
        LambdaQueryWrapper<Headline> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(hid != null, Headline::getHid, hid);
        Headline headline = headlineMapper.selectOne(queryWrapper);
        Map<String, Object> map = new HashMap<>();
        map.put("headline",headline);
        return Result.ok(map);
    }
}




