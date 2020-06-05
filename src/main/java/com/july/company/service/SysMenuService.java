package com.july.company.service;

import com.july.company.dto.menu.SysMenuDto;
import com.july.company.entity.SysMenu;
import com.baomidou.mybatisplus.extension.service.IService;
import com.july.company.vo.menu.SysMenuVo;

import java.util.List;

/**
 * 系统菜单服务类
 * @author zengxueqi
 * @since 2020-06-03
 */
public interface SysMenuService extends IService<SysMenu> {

    /**
     * 获取系统菜单信息
     * @param sysMenuDto
     * @return java.util.List<com.july.company.vo.menu.SysMenuVo>
     * @author zengxueqi
     * @since 2020/6/3
     */
    List<SysMenuVo> getSysMenu(SysMenuDto sysMenuDto);

}
