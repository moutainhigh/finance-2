package com.july.company.vo.sms;

import lombok.Data;

import java.io.Serializable;

/**
 * 验证码信息
 * @author zengxueqi
 * @program finance
 * @since 2020-05-18 15:22
 **/
@Data
public class SmsCodeVo implements Serializable {

    /**
     * 验证码是否有效(0=无效，1=有效)
     * @mock 1
     */
    private Integer smsCodeValid;

}
