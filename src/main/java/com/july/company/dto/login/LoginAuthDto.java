package com.july.company.dto.login;

import com.july.company.utils.Md5Utils;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 登录参数
 * @author zengxueqi
 * @date 2019/11/25
 */
@Data
public class LoginAuthDto implements Serializable {

    private static final long serialVersionUID = -5901244272109941519L;

    /**
     * 登录手机号
     * @mock 14708222420
     */
    @NotNull(message = "手机号不能为空")
    private String mobile;
    /**
     * 登录密码
     * @mock 123456
     */
    @NotNull(message = "密码不能为空")
    private String password;
    /**
     * 登录类型(0=表示前端登录，1=表示后台登录)
     */
    private Integer loginType;

    /**
     * 获取密码对应的加密值
     * @param salt
     * @return
     */
    public String getEncryptPassword(String salt) {
        return Md5Utils.generatePassword(this.password, salt);
    }

}
