package com.july.company.controller;

import com.july.company.dto.code.QuerySysCodeDto;
import com.july.company.dto.code.SysCodeDto;
import com.july.company.entity.SysCode;
import com.july.company.response.ResultT;
import com.july.company.service.SysCodeService;
import com.july.company.vo.code.QuerySysCodeVo;
import com.july.company.vo.code.SysCodeVo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Arrays;
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
     * @param sysCodeDto
     * @return com.july.company.response.ResultT<java.util.List < com.july.company.vo.code.SysCodeVo>>
     * @author zengxueqi
     * @since 2020/5/21
     */
    @PostMapping("/getSysCode")
    public ResultT<List<SysCodeVo>> getSysCode(@RequestBody SysCodeDto sysCodeDto) {
        return ResultT.ok(sysCodeService.getSysCode(sysCodeDto));
    }

    /**
     * 一键匹配下拉框数据
     * @param
     * @return com.july.company.response.ResultT<java.lang.String>
     * @author zengxueqi
     * @since 2020/5/22
     */
    @PostMapping("/saveSysCode")
    public ResultT<String> saveSysCode() {
        sysCodeService.saveSysCode();
        return ResultT.ok("操作成功！");
    }

    /**
     * 获取查询条件信息
     * @param querySysCodeDto
     * @return com.july.company.response.ResultT<java.util.List < com.july.company.vo.code.QuerySysCodeVo>>
     * @author zengxueqi
     * @since 2020/5/25
     */
    @PostMapping("/getQuerySysCode")
    public ResultT<List<QuerySysCodeVo>> getQuerySysCode(@RequestBody QuerySysCodeDto querySysCodeDto) {
        return ResultT.ok(sysCodeService.getQuerySysCode(querySysCodeDto));
    }

    /**
     * 根据字典类型获取字典信息(单个codeType)
     * @param sysCodeDto
     * @return com.july.company.response.ResultT<java.util.List < com.july.company.vo.code.SysCodeVo>>
     * @author zengxueqi
     * @since 2020/6/10
     */
    @PostMapping("/getSysCodeByType")
    public ResultT<List<SysCode>> getSysCodeByType(@RequestBody SysCodeDto sysCodeDto) {
        return ResultT.ok(sysCodeService.getSysCodeByType(sysCodeDto.getCodeType()));
    }

    /**
     * 根据字典类型获取字典信息(多个codeType)
     * @param sysCodeDto
     * @return com.july.company.response.ResultT<java.util.List < com.july.company.vo.code.SysCodeVo>>
     * @author zengxueqi
     * @since 2020/6/10
     */
    @PostMapping("/getSysCodeByTypes")
    public ResultT<List<SysCodeVo>> getSysCodeByTypes(@RequestBody SysCodeDto sysCodeDto) {
        return ResultT.ok(sysCodeService.getSysCodeByTypes(sysCodeDto.getCodeType()));
    }

}
