package com.july.company.mapper;

import com.july.company.dto.code.QuerySysCodeDto;
import com.july.company.dto.code.SysCodeDto;
import com.july.company.entity.SysCode;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.july.company.vo.code.SysCodeValueVo;

import java.util.List;

/**
 * 系统字典表 Mapper 接口
 * @author zengxueqi
 * @since 2020-05-17
 */
public interface SysCodeMapper extends BaseMapper<SysCode> {

    /**
     * 列表查询条件信息
     * @param sysCodeDto
     * @return java.util.List<com.july.company.vo.code.SysCodeValueVo>
     * @author zengxueqi
     * @since 2020/5/21
     */
    List<SysCodeValueVo> getSysCode(SysCodeDto sysCodeDto);

    /**
     * 获取查询条件信息
     * @param querySysCodeDto
     * @return java.util.List<com.july.company.entity.SysCode>
     * @author zengxueqi
     * @since 2020/5/25
     */
    List<SysCode> getQuerySysCode(QuerySysCodeDto querySysCodeDto);

    /**
     * 获取有子类的信息
     * @param
     * @return java.util.List<com.july.company.entity.SysCode>
     * @author zengxueqi
     * @since 2020/5/25
     */
    List<SysCode> getChildSysCode(Integer financeType);

    /**
     * 获取所有的字典信息
     * @param querySysCodeDto
     * @return java.util.List<com.july.company.entity.SysCode>
     * @author zengxueqi
     * @since 2020/5/25
     */
    List<SysCode> getAllSysCode(QuerySysCodeDto querySysCodeDto);

}
