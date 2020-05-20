package com.july.company.controller;

import com.july.company.response.ResultT;
import com.july.company.service.SysCodeService;
import com.july.company.vo.code.SysCodeVo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 系统字典表 前端控制器
 * @author zengxueqi
 * @since 2020-05-17
 */
@RestController
@RequestMapping("/sysCode")
public class SysCodeController {

    @Resource
    private SysCodeService sysCodeService;

    /**
     * 列表查询条件信息
     * @param
     * @return com.july.company.response.ResultT<java.util.List<com.july.company.vo.code.SysCodeVo>>
     * @author zengxueqi
     * @since 2020/5/20
     */
    @PostMapping("/getSysCode")
    public ResultT<List<SysCodeVo>> getSysCode(){
        return ResultT.ok(sysCodeService.getSysCode());
    }

}
