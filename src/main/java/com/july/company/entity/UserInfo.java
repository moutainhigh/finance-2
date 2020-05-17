package com.july.company.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.july.company.base.BaseEntity;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * 用户信息
 * @author zengxueqi
 * @since 2020-05-16
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("fc_user_info")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserInfo extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户姓名
     */
    @TableField("username")
    private String username;

    /**
     * 用户密码
     */
    @TableField("password")
    private String password;

    /**
     * 性别(0女，1男)
     */
    @TableField("sex")
    private Integer sex;

    /**
     * 手机号
     */
    @TableField("mobile")
    private String mobile;

    /**
     * 用户头像
     */
    @TableField("avatar")
    private String avatar;

    /**
     * 密码盐值
     */
    @TableField("pwdSalt")
    private String pwdSalt;

}
