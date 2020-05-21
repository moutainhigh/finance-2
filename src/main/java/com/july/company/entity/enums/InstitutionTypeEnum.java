package com.july.company.entity.enums;

import com.july.company.exception.BnException;

/**
 * 机构类型枚举信息
 * @author zengxueqi
 * @since 2020/05/19
 */
public enum InstitutionTypeEnum {

    /**
     * 股权融资
     */
    STOCKRIGHT(0, "股权机构"),
    /**
     * 债券融资
     */
    BOND(1, "债券机构");

    private Integer value;
    private String desc;

    InstitutionTypeEnum(Integer value, String desc) {
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
        for (InstitutionTypeEnum smsCodeEnum : values()) {
            if (smsCodeEnum.getValue().equals(value)) {
                return smsCodeEnum.desc;
            }
        }
        throw BnException.on("value 值不存在:{0}", value);
    }

}
