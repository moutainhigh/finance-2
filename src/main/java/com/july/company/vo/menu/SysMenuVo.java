package com.july.company.vo.menu;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 系统菜单信息
 * @author zengxueqi
 * @program finance
 * @since 2020-06-03 16:51
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SysMenuVo implements Serializable {

    private static final long serialVersionUID = -2062486718680134843L;

    /**
     * 菜单id
     */
    private Long id;
    /**
     * 菜单地址
     */
    private String href;
    /**
     * 菜单图标
     */
    private String icon;
    /**
     * 菜单名称
     */
    private String title;
    /**
     * 父菜单id
     */
    private Long pid;
    @Builder.Default
    private Boolean spread = false;
    /**
     * 子菜单信息
     */
    private List<SysMenuVo> children;

}
