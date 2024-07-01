package com.qing.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @TableName news_user
 */
//@TableName(value ="news_user")
@Data
//@Getter
//@Setter
public class User implements Serializable {
    @TableId
    private Integer uid;

    private String username;

    private String userPwd;

    private String nickName;

    @Version
//    @TableField(select = false)
    private Integer version;

    @TableLogic
//    @TableField(select = false)
    private Integer isDeleted;

    private static final long serialVersionUID = 1L;
}