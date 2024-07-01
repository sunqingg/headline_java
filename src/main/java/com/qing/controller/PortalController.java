package com.qing.controller;

import com.qing.pojo.PortalVo;
import com.qing.service.PortalService;
import com.qing.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("portal")
@CrossOrigin
public class PortalController {

    @Autowired
    private PortalService portalService;

//    @RequestMapping("findNewsPage")
//    public Result findNewsPage(@RequestBody PortalVo portalVo) {
//        return headlineService.findNewsPage(portalVo);
//    }
    @RequestMapping("findAllTypes")
    public Result findAllTypes() {
        return portalService.findAllTypes();
    }

    @RequestMapping("findNewsPage")
    public Result findNewsPage(@RequestBody PortalVo portalVo) {
        return portalService.findNewsPage(portalVo);
    }
    @RequestMapping("showHeadlineDetail")
    public Result showHeadlineDetail(Integer hid) {
        return portalService.showHeadlineDetail(hid);
    }


}
