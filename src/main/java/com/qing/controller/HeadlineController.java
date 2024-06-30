package com.qing.controller;

import com.qing.mapper.HeadlineMapper;
import com.qing.pojo.PortalVo;
import com.qing.service.HeadlineService;
import com.qing.service.impl.HeadlineServiceImpl;
import com.qing.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("portal")
@CrossOrigin
public class HeadlineController {

    @Autowired
    private HeadlineService headlineService;

    @RequestMapping("findNewsPage")
    public Result findNewsPage(@RequestBody PortalVo portalVo) {
        return headlineService.findNewsPage(portalVo);
    }
}
