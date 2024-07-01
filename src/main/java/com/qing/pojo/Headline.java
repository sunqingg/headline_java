package com.qing.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @TableName news_headline
 */
//@TableName(value ="news_headline")
@Data
public class Headline implements Serializable {
    @TableId
    private Integer hid;

    private String title;

    private String article;

    private Integer type;

    private Integer publisher;

    private Integer pageViews;

    private Date createTime;

    private Date updateTime;

    @Version
//    @TableField(select = false)
    private Integer version;

    @TableLogic
//    @TableField(select = false)
    private Integer isDeleted;

    private static final long serialVersionUID = 1L;
//     @TableField(exist = false) 注明非数据库字段属性
}