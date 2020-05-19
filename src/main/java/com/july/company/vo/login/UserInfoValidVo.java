package com.july.company.vo.login;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户信息是否有效
 * @author zengxueqi
 * @program finance
 * @since 2020-05-19 16:07
 **/
@Data
public class UserInfoValidVo implements Serializable {

    private static final long serialVersionUID = 8438595387002575119L;

    /**
     * 是否存在该用户信息(0=不存在，1=存在)
     * @mock 1
     */
    private Integer userValid;

}
