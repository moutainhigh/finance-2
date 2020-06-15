package com.july.company.mapper;

import com.july.company.entity.FinanceStockMatch;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * 股权融资一键匹配 Mapper 接口
 * @author zengxueqi
 * @since 2020-06-08
 */
public interface FinanceStockMatchMapper extends BaseMapper<FinanceStockMatch> {

    /**
     * 获取最新的公司信息
     * @param companyId
     * @return com.july.company.entity.FinanceStockMatch
     * @author zengxueqi
     * @since 2020/6/13
     */
    FinanceStockMatch getNewestMathInfo(Long companyId);

}
