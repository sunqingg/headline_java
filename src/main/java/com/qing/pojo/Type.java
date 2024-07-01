package com.qing.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @TableName news_type
 */
//@TableName(value ="news_type")
@Data
//@Getter
//@Setter
public class Type implements Serializable {
    @TableId
    private Integer tid;

    private String tname;

    @Version
//    @TableField(select = false)
    private Integer version;

    @TableLogic
//    @TableField(select = false)
    private Integer isDeleted;

    private static final long serialVersionUID = 1L;
}