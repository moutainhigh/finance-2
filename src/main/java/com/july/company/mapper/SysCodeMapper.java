package com.july.company.mapper;

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
     * @param
     * @return java.util.List<com.july.company.vo.code.SysCodeValueVo>
     * @author zengxueqi
     * @since 2020/5/20
     */
    List<SysCodeValueVo> getSysCode();

}
