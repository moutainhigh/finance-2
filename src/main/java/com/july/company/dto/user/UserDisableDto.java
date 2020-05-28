package com.july.company.dto.user;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户禁用信息
 * @author zengxueqi
 * @program finance
 * @since 2020-05-27 13:44
 **/
@Data
public class UserDisableDto implements Serializable {

    private static final long serialVersionUID = 6316890409637661578L;

    /**
     * 用户id
     */
    private Long userId;

}
