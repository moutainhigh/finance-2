package com.july.company.vo.menu;

import lombok.Data;

import java.io.Serializable;

/**
 * 菜单信息
 * @author zengxueqi
 * @program finance
 * @since 2020-06-03 17:46
 **/
@Data
public class MenuVo implements Serializable {

    private static final long serialVersionUID = -4486678021947156784L;

    private Long createdTime;
    private Long createId;
    private Boolean delFlag;
    private String href;
    private String icon;
    private String id;
    private String isShow;
    private String level;
    private String name;
    private String parentIds;
    private String permission;
    private Integer sort;
    private String updatedTime;
    private String updateId;

}
