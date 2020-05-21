package com.july.company.vo.login;

import com.july.company.dto.user.UserInfoDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 登录参数
 * @author zengxueqi
 * @since 2020/05/16
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginAuthVo implements Serializable {

    private static final long serialVersionUID = -5901244272109941519L;

    /**
     * 登录凭证
     */
    private String token;

    /**
     * 用户信息
     */
    private UserInfoDto userInfo;

}
