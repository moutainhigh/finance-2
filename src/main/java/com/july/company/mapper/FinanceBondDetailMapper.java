package com.july.company.mapper;

import com.july.company.entity.FinanceBondDetail;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * 融资公司明细信息 Mapper 接口
 * @author zengxueqi
 * @since 2020-05-26
 */
public interface FinanceBondDetailMapper extends BaseMapper<FinanceBondDetail> {

    /**
     * 获取债券融资产品信息
     * @param productId
     * @return com.july.company.entity.FinanceBondDetail
     * @author zengxueqi
     * @since 2020/6/17
     */
    FinanceBondDetail getFinanceProductDetail(Long productId);

}
