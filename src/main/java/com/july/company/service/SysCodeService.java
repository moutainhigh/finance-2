package com.july.company.service;

import com.july.company.dto.code.QuerySysCodeDto;
import com.july.company.dto.code.SysCodeDto;
import com.july.company.entity.SysCode;
import com.baomidou.mybatisplus.extension.service.IService;
import com.july.company.vo.code.QuerySysCodeVo;
import com.july.company.vo.code.SysCodeVo;

import java.util.List;

/**
 * 系统字典表 服务类
 * @author zengxueqi
 * @since 2020-05-17
 */
public interface SysCodeService extends IService<SysCode> {

    /**
     * 列表查询条件信息
     * @param sysCodeDto
     * @return java.util.List<com.july.company.vo.code.SysCodeVo>
     * @author zengxueqi
     * @since 2020/5/21
     */
    List<SysCodeVo> getSysCode(SysCodeDto sysCodeDto);

    /**
     * 保存一键匹配的下拉框信息
     * @param
     * @return void
     * @author zengxueqi
     * @since 2020/5/22
     */
    void saveSysCode();

    /**
     * 获取查询条件信息
     * @param querySysCodeDto
     * @return java.util.List<com.july.company.vo.code.QuerySysCodeVo>
     * @author zengxueqi
     * @since 2020/5/25
     */
    List<QuerySysCodeVo> getQuerySysCode(QuerySysCodeDto querySysCodeDto);

}
