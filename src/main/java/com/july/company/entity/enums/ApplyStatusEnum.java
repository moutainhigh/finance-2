package com.july.company.entity.enums;

import com.july.company.exception.BnException;

/**
 * 产品申请状态枚举信息=>申请状态（0=待审核，1=已通过，2=已驳回）
 * @author zengxueqi
 * @since 2020/05/19
 */
public enum ApplyStatusEnum {

    /**
     * 待审核
     */
    AUDIT(0, "待审核"),
    /**
     * 已通过
     */
    PASS(1, "已通过"),
    /**
     * 已驳回
     */
    REJECT(2, "已驳回");

    private Integer value;
    private String desc;

    ApplyStatusEnum(Integer value, String desc) {
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
        for (ApplyStatusEnum smsCodeEnum : values()) {
            if (smsCodeEnum.getValue().equals(value)) {
                return smsCodeEnum.desc;
            }
        }
        throw BnException.on("value 值不存在:{0}", value);
    }

}
