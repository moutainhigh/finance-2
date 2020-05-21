package com.july.company.entity.enums;

import com.july.company.exception.BnException;

/**
 * 短信类型枚举信息
 * @author zengxueqi
 * @since 2020/05/19
 */
public enum SmsCodeEnum {

    /**
     * 注册用户
     */
    REGISTER(0, "register_"),
    /**
     * 忘记密码
     */
    FORGET(1, "forgetPassword_");

    private Integer value;
    private String desc;

    SmsCodeEnum(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public Integer getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "" + value;
    }

    public String getDesc() {
        return desc;
    }

    /**
     * 通过枚举value获取desc
     * @param value
     * @return java.lang.String
     * @author zengxueqi
     * @since 2020/3/25
     */
    public static String getDescByValue(Integer value) {
        for (SmsCodeEnum smsCodeEnum : values()) {
            if (smsCodeEnum.getValue().equals(value)) {
                return smsCodeEnum.desc;
            }
        }
        throw BnException.on("value 值不存在:{0}", value);
    }

}
