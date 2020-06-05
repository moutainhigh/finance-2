package com.july.company.entity;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.july.company.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
/**
 * <p>
 * 用户与角色关系
 * </p>
 *
 * @author zengxueqi
 * @since 2020-06-05
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("fc_user_role")
public class UserRole extends BaseEntity{

    private static final long serialVersionUID = 1L;

    @TableField("id")
    private Long id;

    /**
     * 用户id
     */
    @TableId(value = "userId", type = IdType.AUTO)
    private Long userId;

    /**
     * 角色id
     */
    @TableField("roleId")
    private Long roleId;

}
