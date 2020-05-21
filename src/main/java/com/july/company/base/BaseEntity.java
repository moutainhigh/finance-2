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

    public Long getCreatedTime() {
        return System.currentTimeMillis() / 1000;
    }

    public Long getUpdatedTime() {
        return System.currentTimeMillis() / 1000;
    }

    public Integer getDeleted() {
        return 0;
    }

}
