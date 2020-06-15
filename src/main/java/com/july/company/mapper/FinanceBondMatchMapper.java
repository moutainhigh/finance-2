package com.july.company.mapper;

import com.july.company.entity.FinanceBondMatch;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 债券融资一键匹配 Mapper 接口
 * </p>
 *
 * @author zengxueqi
 * @since 2020-06-08
 */
public interface FinanceBondMatchMapper extends BaseMapper<FinanceBondMatch> {

    /**
     * 获取最新的公司信息
     * @param companyId
     * @return com.july.company.entity.FinanceBondMatch
     * @author zengxueqi
     * @since 2020/6/13
     */
    FinanceBondMatch getNewestMathInfo(Long companyId);
}
