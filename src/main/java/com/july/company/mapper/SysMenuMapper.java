package com.july.company.mapper;

import com.july.company.dto.menu.SysMenuDto;
import com.july.company.entity.SysMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.july.company.vo.menu.SysMenuVo;

import java.util.List;
import java.util.Map;

/**
 * 系统菜单Mapper接口
 * @author zengxueqi
 * @since 2020-06-03
 */
public interface SysMenuMapper extends BaseMapper<SysMenu> {

    /**
     * 获取系统菜单信息
     * @param sysMenuDto
     * @return java.util.List<com.july.company.vo.menu.SysMenuVo>
     * @author zengxueqi
     * @since 2020/6/3
     */
    List<SysMenuVo> getSysMenu(SysMenuDto sysMenuDto);

    /**
     * 获取所有的系统菜单信息
     * @param sysMenuDto
     * @return java.util.List<com.july.company.vo.menu.SysMenuVo>
     * @author zengxueqi
     * @since 2020/6/3
     */
    List<SysMenu> getAllSysMenu(SysMenuDto sysMenuDto);

    List<SysMenu> getMenus(Map map);

}
