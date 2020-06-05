package com.july.company.service.impl;

import com.google.common.collect.Maps;
import com.july.company.dto.menu.SysMenuDto;
import com.july.company.entity.SysMenu;
import com.july.company.mapper.SysMenuMapper;
import com.july.company.service.SysMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.july.company.vo.menu.SysMenuVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 系统菜单服务实现类
 * @author zengxueqi
 * @since 2020-06-03
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {

    @Resource
    private SysMenuMapper sysMenuMapper;

    /**
     * 获取系统菜单信息
     * @param sysMenuDto
     * @return java.util.List<com.july.company.vo.menu.SysMenuVo>
     * @author zengxueqi
     * @since 2020/6/3
     */
    @Override
    public List<SysMenuVo> getSysMenu(SysMenuDto sysMenuDto) {
        List<SysMenuVo> sysMenuVoList = sysMenuMapper.getSysMenu(sysMenuDto);
        List<SysMenu> sysMenuList = sysMenuMapper.getAllSysMenu(sysMenuDto);

        return sysMenuVoList.stream().map(sysMenuVo -> {
            List<SysMenuVo> childrenMenus = new ArrayList<>();
            sysMenuList.stream().forEach(sysMenu -> {
                if (sysMenuVo.getId().equals(sysMenu.getParentId())) {
                    childrenMenus.add(SysMenuVo.builder()
                            .icon(sysMenu.getIcon())
                            .id(sysMenu.getId())
                            .href(sysMenu.getHref())
                            .title(sysMenu.getName())
                            .pid(sysMenu.getParentId())
                            .build());
                }
            });
            sysMenuVo.setChildren(childrenMenus);
            return sysMenuVo;
        }).collect(Collectors.toList());
    }

    public List<SysMenu> selectAllMenus() {
        Map<String,Object> map = Maps.newHashMap();
        map.put("parentId",null);
        map.put("isShow",false);
        return sysMenuMapper.getMenus(map);
    }

}
