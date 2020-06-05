package com.july.company.dto.menu;

import lombok.Data;

import java.io.Serializable;

/**
 * 系统菜单信息
 * @author zengxueqi
 * @program finance
 * @since 2020-06-03 17:06
 **/
@Data
public class SysMenuDto implements Serializable {

    private static final long serialVersionUID = 2628273134653566620L;

    /**
     * 父菜单id
     */
    private Long parentId;

}
