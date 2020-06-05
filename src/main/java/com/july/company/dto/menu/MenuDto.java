package com.july.company.dto.menu;

import lombok.Data;

import java.io.Serializable;

/**
 * 菜单信息
 * @author zengxueqi
 * @program finance
 * @since 2020-06-04 10:56
 **/
@Data
public class MenuDto implements Serializable {

    private static final long serialVersionUID = -218620284207614212L;

    /**
     * 菜单地址
     */
    private String menuUrl;

}
