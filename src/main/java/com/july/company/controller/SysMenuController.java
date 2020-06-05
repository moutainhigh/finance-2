package com.july.company.controller;


import com.july.company.dto.menu.SysMenuDto;
import com.july.company.response.ResultT;
import com.july.company.service.SysMenuService;
import com.july.company.vo.menu.SysMenuVo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 系统菜单前端控制器
 * @author zengxueqi
 * @since 2020-06-03
 */
@RestController
@RequestMapping("/sysMenu")
public class SysMenuController {

    @Resource
    private SysMenuService sysMenuService;

    /**
     * 获取系统菜单信息
     * @param
     * @return com.july.company.response.ResultT<java.util.List < com.july.company.vo.menu.SysMenuVo>>
     * @author zengxueqi
     * @since 2020/6/3
     */
    @GetMapping("/getSysMenu")
    public List<SysMenuVo> getSysMenu() {
        return sysMenuService.getSysMenu(null);
    }

}
