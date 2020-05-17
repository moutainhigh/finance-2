package com.july.company.dto.login;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 用户注册信息
 * @author zengxueqi
 * @program springboot-company
 * @since 2020-05-16 10:57
 **/
@Data
public class UserRegisterDto implements Serializable {

    private static final long serialVersionUID = -5250738958171639428L;

    /**
     * 用户名
     */
    @NotNull
    private String userName;
    /**
     * 密码
     */
    @NotNull
    private String password;
    /**
     * 手机号
     */
    @NotNull
    private String mobile;

}
