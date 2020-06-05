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
 * 
 * </p>
 *
 * @author zengxueqi
 * @since 2020-06-03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("fc_sys_menu")
public class SysMenu extends BaseEntity{

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 菜单名称
     */
    @TableField("name")
    private String name;

    /**
     * 父菜单
     */
    @TableField("parent_id")
    private Long parentId;

    /**
     * 菜单层级
     */
    @TableField("level")
    private Long level;

    /**
     * 父菜单联集
     */
    @TableField("parent_ids")
    private String parentIds;

    /**
     * 排序
     */
    @TableField("sort")
    private Integer sort;

    /**
     * 链接地址
     */
    @TableField("href")
    private String href;

    /**
     * 打开方式
     */
    @TableField("target")
    private String target;

    /**
     * 菜单图标
     */
    @TableField("icon")
    private String icon;

    /**
     * 显示背景色
     */
    @TableField("bg_color")
    private String bgColor;

    /**
     * 是否显示
     */
    @TableField("is_show")
    private Integer isShow;

    /**
     * 权限标识
     */
    @TableField("permission")
    private String permission;

    @TableField("remarks")
    private String remarks;

}
