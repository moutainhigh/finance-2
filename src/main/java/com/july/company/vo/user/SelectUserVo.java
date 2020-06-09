package com.july.company.vo.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 用户信息
 * @author zengxueqi
 * @program finance
 * @since 2020-06-08 15:25
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SelectUserVo implements Serializable {

    private static final long serialVersionUID = 3289535056644221564L;

    /**
     * 用户id
     */
    private Long userId;
    /**
     * 公司名称
     */
    private String companyName;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 性别
     */
    private Integer sex;

}
