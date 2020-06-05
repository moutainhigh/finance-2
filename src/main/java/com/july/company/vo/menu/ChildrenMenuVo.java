package com.july.company.vo.menu;

import lombok.Data;

import java.io.Serializable;

/**
 * 子菜单信息
 * @author zengxueqi
 * @program finance
 * @since 2020-06-03 17:53
 **/
@Data
public class ChildrenMenuVo implements Serializable {

    private static final long serialVersionUID = 7820552986842830246L;

    private String bgColor;
    private String children;
    private String createdTime;
    private Long createId;
    private Boolean delFlag;
    private String href;
    private String icon;
    private Long id;
    private Boolean isShow;
    private String level;
    private String name;
    private Long parentId;
    private String parentIds;
    private String permission;
    private Integer sort;
    private String updatedTime;
    private Long updateId;

}
