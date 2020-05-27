package com.july.company.dto.login;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户退出信息
 * @author zengxueqi
 * @program finance
 * @since 2020-05-27 10:03
 **/
@Data
public class UserLogoutDto implements Serializable {

    private static final long serialVersionUID = -818126092843250128L;

    /**
     * 用户手机号
     */
    private String mobile;

}
