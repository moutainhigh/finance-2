package com.july.company.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 登录信息
 * @author zengxueqi
 * @since 2020/05/16
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserInfoDto implements Serializable {

    private static final long serialVersionUID = -4887085165937071589L;

    /**
     * 名称
     */
    private String userName;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 性别
     */
    private Integer sex;
    /**
     * 用户ID
     */
    private Long id;
    /**
     * 登录key
     */
    private String token;

}
