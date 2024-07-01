package com.qing.controller;

import com.qing.pojo.Headline;
import com.qing.pojo.HeadlineVo;
import com.qing.service.HeadlineService;
import com.qing.utils.JwtHelper;
import com.qing.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("headline")
@CrossOrigin
public class HeadlineController {
    @Autowired
    private HeadlineService headlineService;

    @Autowired
    private JwtHelper jwtHelper;

    @RequestMapping("publish")
    public Result publish(@RequestBody HeadlineVo headline, @RequestHeader String token) {
//        return headlineService.publish(headlineVo,token);
//        return Result.ok(null);
        int userId = jwtHelper.getUserId(token).intValue();
//        headline.setPublisher(userId);
        Result result = headlineService.publish(headline,userId);
        return result;
    }

    @RequestMapping("findHeadlineByHid")
    public Result findHeadlineByHid(String hid) {
        return headlineService.findHeadlineByHid(hid);
    }

    @RequestMapping("update")
    public Result update(@RequestBody Headline headline) {
        return headlineService.update(headline);
    }

    @RequestMapping("removeByHid")
    public Result removeByHid(Integer hid) {
        return headlineService.removeByHid(hid);
    }
}
