package com.qing.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
// 或略未知的字段
//@JsonIgnoreProperties(ignoreUnknown = true)
public class PortalVo {
    private int pageNum =1;
    private int pageSize = 10;
}
