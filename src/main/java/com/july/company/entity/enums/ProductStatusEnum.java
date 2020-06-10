package com.july.company.entity.enums;

import com.july.company.exception.BnException;

/**
 * 产品状态枚举信息=>产品状态(驳回=-1，保存=0，待审核=1，已发布=2，已下线=3，已删除=4)
 * @author zengxueqi
 * @since 2020/05/19
 */
public enum ProductStatusEnum {

    /**
     * 驳回
     */
    REJECT(-1, "驳回"),
    /**
     * 保存
     */
    SAVE(0, "草稿"),
    /**
     * 审核
     */
    AUDIT(1, "审核"),
    /**
     * 已发布
     */
    RELEASE(2, "已发布"),
    /**
     * 已下线
     */
    OFFLINE(3, "已下线"),
    /**
     * 已删除
     */
    DELETED(4, "已删除");

    private Integer value;
    private String desc;

    ProductStatusEnum(Integer value, String desc) {
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
        for (ProductStatusEnum smsCodeEnum : values()) {
            if (smsCodeEnum.getValue().equals(value)) {
                return smsCodeEnum.desc;
            }
        }
        throw BnException.on("value 值不存在:{0}", value);
    }

}
