package com.july.company.dto.user;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户信息
 * @author zengxueqi
 * @program finance
 * @since 2020-06-08 15:24
 **/
@Data
public class SelectUserDto implements Serializable {

    private static final long serialVersionUID = 4332585482998009084L;

    /**
     * 用户id
     */
    private Long userId;

}
