package com.july.company.dto.sms;

import lombok.Data;

import java.io.Serializable;

/**
 * 短信信息
 * @author zengxueqi
 * @program finance
 * @since 2020-05-18 14:19
 **/
@Data
public class SmsCodeDto implements Serializable {

    /**
     * 手机号
     */
    private String mobile;

}
