package com.july.company.dto.user;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户信息
 * @author zengxueqi
 * @program finance
 * @since 2020-06-08 16:08
 **/
@Data
public class UpdateUserDto implements Serializable {

    private static final long serialVersionUID = -790432373477541567L;

    /**
     * 用户id
     */
    private Long userId;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 性别
     */
    private Integer sex;

}
