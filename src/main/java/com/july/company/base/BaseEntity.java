package com.july.company.base;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.july.company.utils.DateUtils;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 公共实体类
 * @author zengxueqi
 * @since 2020/05/01
 */
@Setter
@Getter
@ToString
@Accessors(chain = true)
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = 55030697830101880L;
    /**
     * 更新数据的时间(秒)
     */
    @TableField(value = "updatedTime", fill = FieldFill.INSERT_UPDATE)
    private Long updatedTime;
    /**
     * 创建数据的时间(秒)
     */
    @TableField(value = "createdTime", fill = FieldFill.INSERT)
    private Long createdTime;
    /**
     * 删除数据的标志: 1 表示已经删除，0 未删除
     */
    @TableField(value = "deleted", fill = FieldFill.INSERT)
    private Integer deleted;

    /**
     * 获取时间类型的修改时间
     * @return 时间类型的修改时间
     */
    public LocalDateTime updatedTime() {
        if (updatedTime != null) {
            return DateUtils.toLocalDateTime(updatedTime);
        }
        return null;
    }

    /**
     * 把日期时间转换为Long整形的秒
     * @param updatedTime 日期时间
     */
    public void updatedTime(LocalDateTime updatedTime) {
        this.updatedTime = DateUtils.toLong(updatedTime);
    }

    /**
     * 获取时间类型的创建时间
     * @return 创建时间
     */
    public LocalDateTime createdTime() {
        if (createdTime != null) {
            return DateUtils.toLocalDateTime(createdTime);
        }
        return null;
    }

    /**
     * 把日期时间转换为Long整形的秒
     * @param createdTime 日期时间
     */
    public void createdTime(LocalDateTime createdTime) {
        this.createdTime = DateUtils.toLong(createdTime);
    }

}
