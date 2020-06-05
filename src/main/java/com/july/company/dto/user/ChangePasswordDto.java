package com.july.company.dto.user;

import lombok.Data;

import java.io.Serializable;

/**
 * 修改用户密码信息
 * @author zengxueqi
 * @program finance
 * @since 2020-06-05 14:44
 **/
@Data
public class ChangePasswordDto implements Serializable {

    private static final long serialVersionUID = 4745308242078367452L;

    /**
     * 手机号
     */
    private String mobile;
    /**
     * 原始密码
     */
    private String oldPassword;
    /**
     * 新密码
     */
    private String newPassword;

}
